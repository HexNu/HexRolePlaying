package nu.hex.filehandler.node;

import hex.rpg.stf.ContentType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hln
 */
public class TextSegmentTreeBuilder {

    private final String stffDoc;
    private TextSegment campaign;
    private final List<TextSegment> campaignSupplements = new ArrayList<>();
    private final Map<Integer, TextSegment> storyMap = new HashMap<>();
    private final List<TextSegment> storySupplements = new ArrayList<>();
    private final Map<Integer, TextSegment> episodeMap = new HashMap<>();
    private final List<TextSegment> episodeSupplements = new ArrayList<>();
    private final Map<Integer, TextSegment> characterMap = new HashMap<>();
    private final List<TextSegment> characterSupplements = new ArrayList<>();

    public TextSegmentTreeBuilder(String stffDoc) {
        this.stffDoc = stffDoc;
        buildTree();
    }
    
    public TextSegment getRootSegment() {
        return campaign;
    }

    private void parseSegments() {
        StringBuilder lineCollector = null;
        for (String line : stffDoc.split("\n")) {
            if (ContentType.getTypeStrings().contains(line)) {
                if (lineCollector != null && !lineCollector.toString().equals("")) {
                    add(new TextSegment(lineCollector.toString()));
                }
                lineCollector = new StringBuilder(line).append("\n");
            } else {
                if (lineCollector != null) {
                    lineCollector.append(line).append("\n");
                }
            }
        }
    }

    private void buildTree() {
        parseSegments();
        for (TextSegment campaignSupplementSegment : campaignSupplements) {
            campaignSupplementSegment.setParent(campaign);
            campaign.addChild(campaignSupplementSegment);
        }
        for (TextSegment storySegment : storyMap.values()) {
            storySegment.setParent(campaign);
            campaign.addChild(storySegment);
        }
        for (TextSegment storySupplementSegment : storySupplements) {
            TextSegment storySegment = storyMap.get(storySupplementSegment.getParentId());
            storySegment.addChild(storySupplementSegment);
            storySupplementSegment.setParent(storySegment);
        }
        for (TextSegment episodeSegment : episodeMap.values()) {
            TextSegment storySegment = storyMap.get(episodeSegment.getParentId());
            storySegment.addChild(episodeSegment);
            episodeSegment.setParent(storySegment);
        }
        for (TextSegment episodeSupplementSegment : episodeSupplements) {
            TextSegment episodeSegment = episodeMap.get(episodeSupplementSegment.getParentId());
            episodeSegment.addChild(episodeSegment);
            episodeSupplementSegment.setParent(episodeSegment);
        }
        for (TextSegment characterSegment : characterMap.values()) {
            characterSegment.setParent(campaign);
            campaign.addChild(characterSegment);
        }
        for (TextSegment characterSupplementSegment : characterSupplements) {
            TextSegment characterSegment = characterMap.get(characterSupplementSegment.getParentId());
            characterSupplementSegment.setParent(characterSegment);
            characterSegment.addChild(characterSupplementSegment);
        }
    }

    private void add(TextSegment segment) {
        if (segment.getType().equals(ContentType.CAMPAIGN)) {
            this.campaign = segment;
        } else if (segment.getType().equals(ContentType.CAMPAIGN_SUPPLEMENT)) {
            this.campaignSupplements.add(segment);
        } else if (segment.getType().equals(ContentType.STORY)) {
            this.storyMap.put(segment.getId(), segment);
        } else if (segment.getType().equals(ContentType.STORY_SUPPLEMENT)) {
            this.storySupplements.add(segment);
        } else if (segment.getType().equals(ContentType.EPISODE)) {
            this.episodeMap.put(segment.getId(), segment);
        } else if (segment.getType().equals(ContentType.EPISODE_SUPPLEMENT)) {
            this.episodeSupplements.add(segment);
        } else if (segment.getType().equals(ContentType.NON_PLAYING_CHARACTER)) {
            this.characterMap.put(segment.getId(), segment);
        } else if (segment.getType().equals(ContentType.EPISODE_SUPPLEMENT)) {
            this.characterSupplements.add(segment);
        }
    }
}
