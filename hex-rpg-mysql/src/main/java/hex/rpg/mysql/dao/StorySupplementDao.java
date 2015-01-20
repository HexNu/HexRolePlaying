package hex.rpg.mysql.dao;

import hex.rpg.core.domain.story.StorySupplement;
import hex.rpg.jpa.domain.story.impl.RpgStorySupplement;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class StorySupplementDao extends GenericDao<StorySupplement, Long> {

    public StorySupplementDao(EntityManager entityManager) {
        super(RpgStorySupplement.class, entityManager);
    }

}
