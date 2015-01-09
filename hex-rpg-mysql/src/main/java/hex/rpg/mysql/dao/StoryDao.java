package hex.rpg.mysql.dao;

import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.impl.RpgStory;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class StoryDao extends GenericDao<Story, Long> {

    public StoryDao(EntityManager entityManager) {
        super(RpgStory.class, entityManager);
    }
}
