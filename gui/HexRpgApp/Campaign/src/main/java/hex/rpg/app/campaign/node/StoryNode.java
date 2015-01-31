package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.EditStoryAction;
import hex.rpg.app.domain.story.AppStory;

/**
 *
 * @author hln
 */
public class StoryNode extends AbstractRpgNode<AppStory> {

    public StoryNode(AppStory entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        addAction(new EditStoryAction(getEntity()));
    }

}
