package hex.rpg.dto.out;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class FullCampaignDTO extends AbstractDTO {

    private final Long id;
    private final String description;
    private final String shortDescription;
    private final String title;
    private final String refereeInfo;
    private final String refereeNotes;
    private final boolean hasSupplements;
    private final boolean hasStories;
    private final boolean hasCharacters;
    private final Campaign.Type type;
    private final List<FullSupplementDTO> supplements = new ArrayList<>();
    private final List<FullStoryDTO> stories = new ArrayList<>();
    private final List<FullNonPlayingCharacterDTO> characters = new ArrayList<>();

    public FullCampaignDTO(Campaign campaign, LinkDTOBuilder linkBuilder) {
        id = campaign.getId();
        title = campaign.getTitle();
        type = campaign.getType();
        shortDescription = campaign.getShortDescription();
        description = campaign.getDescription();
        refereeInfo = campaign.getRefereeInfo();
        refereeNotes = campaign.getRefereeNotes();
        hasSupplements = campaign.hasSupplements();
        hasStories = campaign.hasStories();
        hasCharacters = campaign.hasCharacters();
        campaign.getSupplements().stream().forEach((supplement) -> {
            supplements.add(new FullSupplementDTO(supplement, linkBuilder));
        });
        campaign.getStories().stream().forEach((story) -> {
            stories.add(new FullStoryDTO(story, linkBuilder));
        });
        campaign.getCharacters().stream().forEach((character) -> {
            characters.add(new FullNonPlayingCharacterDTO(character, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public String getRefereeInfo() {
        return refereeInfo;
    }

    public String getRefereeNotes() {
        return refereeNotes;
    }

    public Campaign.Type getType() {
        return type;
    }

    public List<FullSupplementDTO> getSupplements() {
        return supplements;
    }

    public List<FullStoryDTO> getStories() {
        return stories;
    }

    public List<FullNonPlayingCharacterDTO> getCharacters() {
        return characters;
    }

    public boolean isHasSupplements() {
        return hasSupplements;
    }

    public boolean isHasStories() {
        return hasStories;
    }

    public boolean isHasCharacters() {
        return hasCharacters;
    }
}
