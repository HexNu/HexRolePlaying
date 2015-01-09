package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.story.Story;

/**
 *
 * @author hln
 */
public class StoryListItemDTO extends AbstractDTO {

    private final String title;
    private final String shortDescription;

    public StoryListItemDTO(Story story, LinkDTOBuilder linkBuilder) {
        addLink(linkBuilder.createStoryLink("view", story));
        title = story.getTitle();
        shortDescription = story.getShortDescription();
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
