package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class SavePlayingCharacterCommand extends AbstractServiceCommand<PlayingCharacter> {
    private final PlayingCharacter character;

    public SavePlayingCharacterCommand(PlayingCharacter character) {
        this.character = character;
    }

    @Override
    public PlayingCharacter execute() {
        return getDaoFactory().getPlayingCharacterDao().save(character);
    }
}
