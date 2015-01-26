package hex.rpg.app.domain.story;

import hex.rpg.app.domain.AbstractSupplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;

/**
 *
 * @author hln
 */
public class AppStorySupplement extends AbstractSupplement implements StorySupplement {
    private Story story;

    @Override
    protected String getOwnerClassName() {
        return "Story";
    }

    @Override
    public Long getParentId() {
        return story != null ? story.getId() : null;
    }

    @Override
    public Story getStory() {
        return story;
    }

    @Override
    public void setStory(Story story) {
        this.story = story;
    }

}
