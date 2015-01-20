package hex.rpg.mysql.dao;

import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.jpa.domain.character.impl.RpgPlayingCharacter;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class PlayingCharacterDao extends GenericDao<PlayingCharacter, Long> {

    public PlayingCharacterDao(EntityManager entityManager) {
        super(RpgPlayingCharacter.class, entityManager);
    }

    @Override
    public PlayingCharacter save(PlayingCharacter character) {
        if (character.getId() == null) {
            return super.save(character);
        } else {
            return super.update(character);
        }
    }
}
