package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetNonPlayingCharacterCommand extends AbstractServiceCommand<NonPlayingCharacter> {
    private final Long id;

    public GetNonPlayingCharacterCommand(Long id) {
        this.id = id;
    }

    @Override
    public NonPlayingCharacter execute() {
        return getDaoFactory().getNonPlayingCharacterDao().findByPrimaryKey(id);
    }
}
