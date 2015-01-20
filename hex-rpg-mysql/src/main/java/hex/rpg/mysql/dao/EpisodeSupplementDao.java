package hex.rpg.mysql.dao;

import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.jpa.domain.story.impl.RpgEpisodeSupplement;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class EpisodeSupplementDao extends GenericDao<EpisodeSupplement, Long> {

    public EpisodeSupplementDao(EntityManager entityManager) {
        super(RpgEpisodeSupplement.class, entityManager);
    }

}
