package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class FullStoryDTO extends AbstractDTO {

    private final Long id;
    private final String title;
    private final Integer index;
    private final String shortDescription;
    private final String description;
    private final String refereeInfo;
    private final String refereeNotes;
    private final List<FullEpisodeDTO> episodes = new ArrayList<>();
    private final List<FullSupplementDTO> supplements = new ArrayList<>();

    public FullStoryDTO(Story story, LinkDTOBuilder linkBuilder) {
        id = story.getId();
        title = story.getTitle();
        index = story.getIndex();
        shortDescription = story.getShortDescription();
        description = story.getDescription();
        refereeInfo = story.getRefereeInfo();
        refereeNotes = story.getRefereeNotes();
        story.getSupplements().stream().forEach((supplement) -> {
            supplements.add(new FullSupplementDTO(supplement, linkBuilder));
        });
        story.getEpisodes().stream().forEach((episode) -> {
            episodes.add(new FullEpisodeDTO(episode, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIndex() {
        return index;
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

    public List<FullEpisodeDTO> getEpisodes() {
        return episodes;
    }

    public List<FullSupplementDTO> getSupplements() {
        return supplements;
    }

}
