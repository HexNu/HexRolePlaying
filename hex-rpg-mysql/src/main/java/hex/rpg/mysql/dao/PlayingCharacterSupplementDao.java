package hex.rpg.mysql.dao;

import hex.rpg.core.domain.character.PlayingCharacterSupplement;
import hex.rpg.jpa.domain.character.impl.RpgPlayingCharacterSupplement;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class PlayingCharacterSupplementDao extends GenericDao<PlayingCharacterSupplement, Long> {

    public PlayingCharacterSupplementDao(EntityManager entityManager) {
        super(RpgPlayingCharacterSupplement.class, entityManager);
    }

}
