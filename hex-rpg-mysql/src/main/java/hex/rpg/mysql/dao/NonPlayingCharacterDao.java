package hex.rpg.mysql.dao;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.impl.RpgNonPlayingCharacter;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterDao extends GenericDao<NonPlayingCharacter, Long> {

    public NonPlayingCharacterDao(EntityManager entityManager) {
        super(RpgNonPlayingCharacter.class, entityManager);
    }

    @Override
    public NonPlayingCharacter save(NonPlayingCharacter character) {
        if (character.getId() == null) {
            return super.save(character);
        } else {
            return super.update(character);
        }
    }
}
