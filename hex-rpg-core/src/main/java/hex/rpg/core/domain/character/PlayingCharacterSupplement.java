package hex.rpg.core.domain.character;

import hex.rpg.core.domain.Supplement;

/**
 *
 * @author hln
 */
public interface PlayingCharacterSupplement extends Supplement {

    PlayingCharacter getPlayingCharacter();
    
    void setPlayingCharacter(PlayingCharacter character);
}
