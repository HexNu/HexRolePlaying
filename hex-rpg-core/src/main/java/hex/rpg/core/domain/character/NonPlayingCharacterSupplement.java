package hex.rpg.core.domain.character;

import hex.rpg.core.domain.Supplement;

/**
 *
 * @author hln
 */
public interface NonPlayingCharacterSupplement extends Supplement {

    NonPlayingCharacter getCharacter();
    
    void setCharacter(NonPlayingCharacter character);
}
