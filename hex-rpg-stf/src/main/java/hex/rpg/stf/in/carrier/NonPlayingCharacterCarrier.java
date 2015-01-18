package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.character.NonPlayingCharacter;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterCarrier extends AbstractEntityCarrier<NonPlayingCharacter> {

    public NonPlayingCharacterCarrier(Long tempId, Long tempParentId, NonPlayingCharacter entity) {
        super(tempId, tempParentId, entity);
    }

}
