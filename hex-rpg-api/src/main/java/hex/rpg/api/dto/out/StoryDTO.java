package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class StoryDTO extends AbstractDTO {

    private final Long id;
    private final String title;
    private final String shortDescription;
    private final String description;
    private final String refereeInfo;
    private final String refereeNotes;
    private final List<EpisodeListItemDTO> episodes = new ArrayList<>();

    public StoryDTO(Story story, LinkDTOBuilder linkBuilder) {
        id = story.getId();
        title = story.getTitle();
        shortDescription = story.getShortDescription();
        description = story.getDescription();
        refereeInfo = story.getRefereeInfo();
        refereeNotes = story.getRefereeNotes();
        story.getEpisodes().stream().forEach((episode) -> {
            episodes.add(new EpisodeListItemDTO(episode, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public List<EpisodeListItemDTO> getEpisodes() {
        return episodes;
    }
}
