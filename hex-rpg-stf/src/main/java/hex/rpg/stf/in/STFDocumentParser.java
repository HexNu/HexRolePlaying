package hex.rpg.stf.in;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.impl.RpgNonPlayingCharacter;
import hex.rpg.core.domain.character.impl.RpgNonPlayingCharacterSupplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.impl.RpgEpisode;
import hex.rpg.core.domain.story.impl.RpgEpisodeSupplement;
import hex.rpg.core.domain.story.impl.RpgStory;
import hex.rpg.core.domain.story.impl.RpgStorySupplement;
import hex.rpg.stf.ContentType;
import hex.rpg.stf.Field;
import hex.rpg.stf.Parsing;
import hex.rpg.stf.Segment;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.digitman.util.DateUtilities;

/**
 *
 * @author hln
 */
public class STFDocumentParser {

    private final InputStream stream;
    private final CampaignCarrier campaignCarrier = new CampaignCarrier();
    private String basePath;

    public STFDocumentParser(InputStream stream) {
        this(stream, null);
    }

    public STFDocumentParser(InputStream stream, String basePath) {
        this.stream = stream;
        setBasePath(basePath);
    }

    public boolean parse() {
        if (stream != null) {
            InputStreamReader reader;
            try {
                reader = new InputStreamReader(stream, Constants.UTF_8);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            BufferedReader bufferedReader = new BufferedReader(reader);
            String currentLine;
            boolean collectingEntityData = false;
            StringBuilder lineCollector = new StringBuilder();
            try {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (currentLine.matches(Parsing.REGEX_PROCESSING_INSTRUCTION)) {
                        if (currentLine.matches(Parsing.REGEX_BASEPATH)) {
                            setBasePath(currentLine);
                        }
                    }
                    if (collectingEntityData && currentLine.matches(Segment.REGEX_END_OF_SEGMENT)) {
                        parseData(lineCollector.toString());
                        collectingEntityData = false;
                        lineCollector = new StringBuilder();
                    }
                    if (marksNewSegment(currentLine)) {
                        collectingEntityData = true;
                    }
                    if (collectingEntityData) {
                        lineCollector.append(currentLine).append("\n");
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    private void setBasePath(String basePath) {
        if (basePath != null) {
            basePath = basePath.split(Parsing.Instruction.BASEPATH.splitRegex())[1].trim();
            if (!basePath.endsWith("/")) {
                basePath += "/";
            }
        }
        this.basePath = basePath;
    }

    private DomainEntity parseData(String entityData) {
        List<String> lines = Arrays.asList(entityData.split("\n"));
        DomainEntity entity = createEntity(lines.get(0));
        String currentField = null;
        Long currentId = null;
        Long currentParentId = null;
        StringBuilder content = new StringBuilder();
        for (String line : lines) {
            if (line.matches(Segment.REGEX_NEW_FIELD)) {
                putFieldValue(entity, currentField, content.toString());
                content = new StringBuilder();
                currentField = line.substring(0, line.indexOf(Field.LABEL_SEPARATOR));
                content.append(line.substring(line.indexOf(Field.LABEL_SEPARATOR) + 1));
            } else if (line.matches(Segment.REGEX_END_OF_SEGMENT)) {
                addToCampaign(currentId, currentParentId, entity);
                currentId = null;
                currentParentId = null;
            } else if (line.startsWith(Field.Label.x + Field.LABEL_SEPARATOR)) {
                currentId = Long.valueOf(line.substring(2));
            } else if (line.startsWith(Field.Label.p + Field.LABEL_SEPARATOR)) {
                currentParentId = Long.valueOf(line.substring(2));
            } else {
                content.append(line);
            }
        }
        return entity;
    }

    private DomainEntity createEntity(String string) {
        if (marksNewSegment(string)) {
            switch (string) {
                case "Campaign":
                    return new RpgCampaign();
                case "CampaignSupplement":
                    return new RpgCampaignSupplement();
                case "Story":
                    return new RpgStory();
                case "StorySupplement":
                    return new RpgStorySupplement();
                case "Episode":
                    return new RpgEpisode();
                case "EpisodeSupplement":
                    return new RpgEpisodeSupplement();
                case "NonPlayingCharacter":
                    return new RpgNonPlayingCharacter();
                case "NonPlayingCharacterSupplement":
                    return new RpgNonPlayingCharacterSupplement();
            }
        }
        return null;
    }

    private void putFieldValue(DomainEntity domainEntity, String field, String content) {
        if (domainEntity instanceof NarrativeEntity) {
            NarrativeEntity entity = (NarrativeEntity) domainEntity;
            if (field.equals(Field.Label.T.name())) {
                entity.setTitle(content);
            }
            if (field.equals(Field.Label.D.name())) {
                entity.setDescription(content);
            }
            if (field.equals(Field.Label.S.name())) {
                entity.setShortDescription(content);
            }
            if (field.equals(Field.Label.I.name())) {
                entity.setRefereeInfo(content);
            }
            if (field.equals(Field.Label.N.name())) {
                entity.setRefereeNotes(content);
            }
            if (field.equals(Field.Label.Y.name())) {
                if (domainEntity instanceof Campaign) {
                    ((Campaign) domainEntity).setType(Campaign.Type.getByString(content));
                } else if (domainEntity instanceof Supplement) {
                    ((Supplement) domainEntity).setType(Supplement.Type.getByString(field));
                }
            }
            if (field.equals(Field.Label.X.name())) {
                if (domainEntity instanceof Story) {
                    ((Story) domainEntity).setIndex(Integer.valueOf(content));
                } else if (domainEntity instanceof Episode) {
                    ((Episode) domainEntity).setIndex(Integer.valueOf(content));
                } else if (domainEntity instanceof Supplement) {
                    ((Supplement) domainEntity).setIndex(Integer.valueOf(content));
                }
            }
            if (field.equals(Field.Label.C.name())) {
                if (domainEntity instanceof Episode) {
                    ((Episode) domainEntity).setContent(content);
                } else if (domainEntity instanceof Supplement) {
                    byte[] supplementContent = createByteArray(content);
                    ((Supplement) domainEntity).setContent(supplementContent);
                }
            }
            if (field.equals(Field.Label.M.name())) {
                if (domainEntity instanceof Supplement) {
                    ((Supplement) domainEntity).setMediaType(content);
                }
            }
            if (field.equals(Field.Label.M.name())) {
                if (domainEntity instanceof Supplement) {
                    ((Supplement) domainEntity).setMediaType(content);
                }
            }
        } else if (domainEntity instanceof NonPlayingCharacter) {
            NonPlayingCharacter entity = (NonPlayingCharacter) domainEntity;
            if (field.equals(Field.Label.B.name())) {
                entity.setBirthdate(new DateUtilities(content).get());
            }
            if (field.equals(Field.Label.T.name())) {
                entity.setName(content);
            }
            if (field.equals(Field.Label.S.name())) {
                entity.setShortDescription(content);
            }
            if (field.equals(Field.Label.D.name())) {
                entity.setDescription(content);
            }
            if (field.equals(Field.Label.G.name())) {
                entity.setGender(CharacterEntity.Gender.getByString(content));
            }
            if (field.equals(Field.Label.H.name())) {
                entity.setHabitation(content);
            }
            if (field.equals(Field.Label.M.name())) {
                entity.setPortraitMediaType(content);
            }
            if (field.equals(Field.Label.C.name())) {
                if (content != null && content.startsWith("/")) {
                    byte[] portrait = createByteArray(content);
                    entity.setPortrait(portrait);
                }
            }
            if (field.equals(Field.Label.O.name())) {
                entity.setOccupation(content);
            }
            if (field.equals(Field.Label.I.name())) {
                entity.setRefereeInfo(content);
            }
            if (field.equals(Field.Label.N.name())) {
                entity.setRefereeNotes(content);
            }
            if (field.equals(Field.Label.R.name())) {
                entity.setSpecies(content);
            }
            if (field.equals(Field.Label.J.name())) {
                entity.setGamingStats(content);
            }
        }
    }

    private byte[] createByteArray(String path) {
        String filePath = path.startsWith("/") ? path.trim() : basePath != null ? basePath + path.trim() : null;
        if (filePath != null) {
            try {
                File contentFile = new File(filePath);
                return getStreamAsByteArray(new FileInputStream(contentFile));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private byte[] getStreamAsByteArray(InputStream inputStream) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        final int BUF_SIZE = 1 << 8; //1KiB buffer
        byte[] buffer = new byte[BUF_SIZE];
        int bytesRead;
        try {
            while ((bytesRead = inputStream.read(buffer)) > -1) {
                out.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            return out.toByteArray();
        } catch (IOException ex) {
            return null;
        }
    }

    private boolean marksNewSegment(String line) {
        for (ContentType type : ContentType.values()) {
            if (type.toString().equals(line)) {
                return true;
            }
        }
        return false;
    }

    private void addToCampaign(Long tempId, Long tempParentId, DomainEntity entity) {
        campaignCarrier.addEntity(tempId, tempParentId, entity);
    }

    public CampaignCarrier getCampaignCarrier() {
        return campaignCarrier;
    }
}
