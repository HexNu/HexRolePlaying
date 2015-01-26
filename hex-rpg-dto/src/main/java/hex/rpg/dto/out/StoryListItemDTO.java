package hex.rpg.dto.out;

import hex.rpg.core.domain.story.Story;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;

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
