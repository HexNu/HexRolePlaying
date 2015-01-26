package hex.rpg.stf.in;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaign;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaignSupplement;
import hex.rpg.jpa.domain.character.impl.RpgNonPlayingCharacter;
import hex.rpg.jpa.domain.character.impl.RpgNonPlayingCharacterSupplement;
import hex.rpg.jpa.domain.story.impl.RpgEpisode;
import hex.rpg.jpa.domain.story.impl.RpgEpisodeSupplement;
import hex.rpg.jpa.domain.story.impl.RpgStory;
import hex.rpg.jpa.domain.story.impl.RpgStorySupplement;
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
    private final CampaignWrapper campaignWrapper = new CampaignWrapper();
    private String basePath;

    public STFDocumentParser(InputStream stream) {
        this.stream = stream;
    }

    public STFDocumentParser(InputStream stream, String basePath) {
        this.stream = stream;
        setBasePath(basePath);
    }

    public Campaign parse() {
        if (stream != null) {
            InputStreamReader reader;
            try {
                reader = new InputStreamReader(stream, Constants.UTF_8);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return null;
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
                    } else if (currentLine.matches(Parsing.REGEX_COMMENT_LINE)) {
                        Logger.getLogger(STFDocumentParser.class.getName()).log(Level.INFO, currentLine);
                    } else {
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
                }
            } catch (IOException ex) {
                Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return campaignWrapper.build();
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
                if (currentField != null) {
                    putFieldValue(entity, currentField, content.toString());
                }
                content = new StringBuilder();
                currentField = line.substring(0, line.indexOf(Field.LABEL_SEPARATOR));
                content.append(line.substring(line.indexOf(Field.LABEL_SEPARATOR) + 1));
            } else {
                content.append(line);
            }
            if (line.startsWith(Field.Label.x + Field.LABEL_SEPARATOR)) {
                currentId = Long.valueOf(line.substring(2));
            } else if (line.startsWith(Field.Label.p + Field.LABEL_SEPARATOR)) {
                currentParentId = Long.valueOf(line.substring(2));
            }
        }
        campaignWrapper.addToWrapper(currentId, currentParentId, entity);
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
            switch (field) {
                case "T":
                    entity.setTitle(content);
                    break;
                case "D":
                    entity.setDescription(content);
                    break;
                case "S":
                    entity.setShortDescription(content);
                    break;
                case "I":
                    entity.setRefereeInfo(content);
                    break;
                case "N":
                    entity.setRefereeNotes(content);
                    break;
                case "Y":
                    if (domainEntity instanceof Campaign) {
                        ((Campaign) domainEntity).setType(Campaign.Type.getByString(content));
                    } else if (domainEntity instanceof Supplement) {
                        ((Supplement) domainEntity).setType(Supplement.Type.getByString(field));
                    }
                    break;
                case "X":
                    if (domainEntity instanceof Story) {
                        ((Story) domainEntity).setIndex(Integer.valueOf(content) - 1);
                    } else if (domainEntity instanceof Episode) {
                        ((Episode) domainEntity).setIndex(Integer.valueOf(content) - 1);
                    } else if (domainEntity instanceof Supplement) {
                        ((Supplement) domainEntity).setIndex(Integer.valueOf(content) - 1);
                    }
                    break;
                case "C":
                    if (domainEntity instanceof Episode) {
                        ((Episode) domainEntity).setContent(content);
                    } else if (domainEntity instanceof Supplement) {
                        byte[] supplementContent = createByteArray(content);
                        ((Supplement) domainEntity).setContent(supplementContent);
                    }
                    break;
                case "M":
                    if (domainEntity instanceof Supplement) {
                        ((Supplement) domainEntity).setMediaType(content);
                    }
                    break;
            }
        } else if (domainEntity instanceof NonPlayingCharacter) {
            NonPlayingCharacter entity = (NonPlayingCharacter) domainEntity;
            switch (field) {
                case "B":
                    entity.setBirthdate(new DateUtilities(content).get());
                    break;
                case "T":
                    entity.setName(content);
                    break;
                case "S":
                    entity.setShortDescription(content);
                    break;
                case "D":
                    entity.setDescription(content);
                    break;
                case "G":
                    entity.setGender(CharacterEntity.Gender.getByString(content));
                    break;
                case "H":
                    entity.setHabitation(content);
                    break;
                case "M":
                    entity.setPortraitMediaType(content);
                    break;
                case "P":
                    if (content != null) {
                        byte[] portrait = createByteArray(content);
                        entity.setPortrait(portrait);
                    }
                    break;
                case "O":
                    entity.setOccupation(content);
                    break;
                case "I":
                    entity.setRefereeInfo(content);
                    break;
                case "N":
                    entity.setRefereeNotes(content);
                    break;
                case "R":
                    entity.setSpecies(content);
                    break;
                case "J":
                    entity.setGamingStats(content);
                    break;
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

    public CampaignWrapper getCampaignWrapper() {
        return campaignWrapper;
    }

    public static void main(String[] args) {
        try {

            String path = "/home/hln/Skrivbord/Wetherington_Hall.stf_FILES/Wetherington_Hall.stff";
            FileInputStream stream = new FileInputStream(new File(path));
            STFDocumentParser parser = new STFDocumentParser(stream);
            Campaign campaign = parser.parse();
            System.out.println(campaign.getTitle());
            for (Story story : campaign.getStories()) {
                System.out.println(story.getTitle());
                for (Supplement supplement : story.getSupplements()) {
                    System.out.println(supplement.getTitle());
                    System.out.println(supplement.getContentAsByteArray().length);
                }
            }
            for (NonPlayingCharacter npc : campaign.getCharacters()) {
                System.out.println(npc.getName());
                System.out.println(npc.getPortraitAsByteArray().length);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(STFDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
