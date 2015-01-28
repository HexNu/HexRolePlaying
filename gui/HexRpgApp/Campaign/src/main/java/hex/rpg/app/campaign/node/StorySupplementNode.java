package hex.rpg.app.campaign.node;

import hex.rpg.app.domain.story.AppStorySupplement;

/**
 *
 * @author hln
 */
public class StorySupplementNode extends AbstractRpgNode<AppStorySupplement> {

    public StorySupplementNode(AppStorySupplement entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        setIcon("file-picture-text");
    }

}
