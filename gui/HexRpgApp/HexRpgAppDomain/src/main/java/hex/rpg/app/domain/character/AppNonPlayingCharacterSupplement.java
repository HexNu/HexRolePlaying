package hex.rpg.app.domain.character;

import hex.rpg.app.domain.AbstractSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;

/**
 *
 * @author hln
 */
public class AppNonPlayingCharacterSupplement extends AbstractSupplement implements NonPlayingCharacterSupplement {

    private NonPlayingCharacter character;

    @Override
    protected String getOwnerClassName() {
        return "NonPlayingCharacter";
    }

    @Override
    public Long getParentId() {
        return character != null ? character.getId() : null;
    }

    @Override
    public NonPlayingCharacter getCharacter() {
        return character;
    }

    @Override
    public void setCharacter(NonPlayingCharacter character) {
        this.character = character;
    }

}
