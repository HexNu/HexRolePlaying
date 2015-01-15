package hex.rpg.stf.in;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaignSupplement;
import hex.rpg.core.domain.character.impl.RpgNonPlayingCharacter;
import hex.rpg.core.domain.character.impl.RpgNonPlayingCharacterSupplement;
import hex.rpg.core.domain.story.impl.RpgEpisode;
import hex.rpg.core.domain.story.impl.RpgEpisodeSupplement;
import hex.rpg.core.domain.story.impl.RpgStory;
import hex.rpg.core.domain.story.impl.RpgStorySupplement;
import hex.rpg.stf.ContentType;
import hex.rpg.stf.Field;
import hex.rpg.stf.Segment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hln
 */
public class SimpleTextFormatDocumentParser {

    private final InputStream stream;

    public SimpleTextFormatDocumentParser(InputStream stream) {
        this.stream = stream;
    }

    public boolean parse() {
        if (stream != null) {
            InputStreamReader reader;
            try {
                reader = new InputStreamReader(stream, Constants.UTF_8);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(SimpleTextFormatDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            BufferedReader bufferedReader = new BufferedReader(reader);
            String currentLine;
            boolean collectingEntityData = false;
            StringBuilder lineCollector = new StringBuilder();
            try {
                while ((currentLine = bufferedReader.readLine()) != null) {
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
                Logger.getLogger(SimpleTextFormatDocumentParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return true;
    }

    private DomainEntity parseData(String entityData) {
        List<String> lines = Arrays.asList(entityData.split("\n"));
        DomainEntity domainEntity = createEntity(lines.get(0));
        String lastField = null;
        StringBuilder content = new StringBuilder();
        if (domainEntity instanceof NarrativeEntity) {
            NarrativeEntity entity = (NarrativeEntity) domainEntity;
            for (String line : lines) {
                if (line.matches(Segment.REGEX_NEW_FIELD)) {
                    putFieldValue(entity, lastField, content.toString());
                    content = new StringBuilder();
                    lastField = line.substring(0, line.indexOf(Field.LABEL_SEPARATOR));
                    content.append(line.substring(line.indexOf(Field.LABEL_SEPARATOR) + 1));
                } else if (line.matches(Segment.REGEX_END_OF_SEGMENT)) {
                    addToCampaign(entity);
                } else {
                    content.append(line);
                }
            }
        }
        return domainEntity;
    }

    private void putFieldValue(NarrativeEntity entity, String field, String content) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    private boolean marksNewSegment(String line) {
        for (ContentType type : ContentType.values()) {
            if (type.toString().equals(line)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String k = "EpisodeSupplement\nx:\np:\nT:Slask\n;\n";

    }

    private void addToCampaign(NarrativeEntity entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
