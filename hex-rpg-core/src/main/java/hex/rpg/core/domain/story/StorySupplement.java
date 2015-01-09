package hex.rpg.core.domain.story;

import hex.rpg.core.domain.Supplement;

/**
 *
 * @author hln
 */
public interface StorySupplement extends Supplement {

    Story getStory();

    void setStory(Story story);
}
