package hex.rpg.mysql.dao;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.jpa.domain.story.impl.RpgEpisode;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class EpisodeDao extends GenericDao<Episode, Long> {

    public EpisodeDao(EntityManager entityManager) {
        super(RpgEpisode.class, entityManager);
    }

}
