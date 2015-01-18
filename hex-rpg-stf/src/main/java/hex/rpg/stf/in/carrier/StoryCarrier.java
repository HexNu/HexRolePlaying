package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.story.Story;

/**
 *
 * @author hln
 */
public class StoryCarrier extends AbstractEntityCarrier<Story> {

    public StoryCarrier(Long tempId, Long tempParentId, Story entity) {
        super(tempId, tempParentId, entity);
    }

}
