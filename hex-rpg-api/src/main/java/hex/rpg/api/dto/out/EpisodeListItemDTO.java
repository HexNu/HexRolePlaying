package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.story.Episode;

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
