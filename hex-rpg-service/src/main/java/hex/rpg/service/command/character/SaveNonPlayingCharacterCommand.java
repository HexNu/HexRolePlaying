package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class SaveNonPlayingCharacterCommand extends AbstractServiceCommand<NonPlayingCharacter> {

    private final NonPlayingCharacter character;

    public SaveNonPlayingCharacterCommand(NonPlayingCharacter character) {
        this.character = character;
    }

    @Override
    public NonPlayingCharacter execute() {
        return getDaoFactory().getNonPlayingCharacterDao().save(character);
    }
}
