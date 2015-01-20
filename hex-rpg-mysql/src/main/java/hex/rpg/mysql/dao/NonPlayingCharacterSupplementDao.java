package hex.rpg.mysql.dao;

import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;
import hex.rpg.jpa.domain.character.impl.RpgNonPlayingCharacterSupplement;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterSupplementDao extends GenericDao<NonPlayingCharacterSupplement, Long> {

    public NonPlayingCharacterSupplementDao(EntityManager entityManager) {
        super(RpgNonPlayingCharacterSupplement.class, entityManager);
    }

}
