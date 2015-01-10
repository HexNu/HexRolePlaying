package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignDTO extends AbstractDTO {
    
    private final Long id;
    private final String title;
    private final String type;
    private final String shortDescription;
    private final String description;
    private final String refereeInfo;
    private final String refereeNotes;
    private final List<StoryListItemDTO> stories = new ArrayList<>();
    

    public CampaignDTO(Campaign campaign, LinkDTOBuilder linkBuilder) {
        id = campaign.getId();
        title = campaign.getTitle();
        type = campaign.getType().getFlavour();
        shortDescription = campaign.getShortDescription();
        description = campaign.getDescription();
        refereeInfo = campaign.getRefereeInfo();
        refereeNotes = campaign.getRefereeNotes();
        campaign.getStories().stream().forEach((story) -> {
            stories.add(new StoryListItemDTO(story, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getRefereeInfo() {
        return refereeInfo;
    }

    public String getRefereeNotes() {
        return refereeNotes;
    }

    public List<StoryListItemDTO> getStories() {
        return stories;
    }
}
