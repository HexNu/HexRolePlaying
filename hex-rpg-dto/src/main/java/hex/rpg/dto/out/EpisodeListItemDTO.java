package hex.rpg.dto.out;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;

/**
 *
 * @author hln
 */
public class EpisodeListItemDTO extends AbstractDTO {

    private final String title;
    private final String shortDescription;

    public EpisodeListItemDTO(Episode episode, LinkDTOBuilder linkBuilder) {
        addLink(linkBuilder.createEpisodeLink("view", episode));
        title = episode.getTitle();
        shortDescription = episode.getShortDescription();
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
