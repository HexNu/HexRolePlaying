package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetPlayingCharacterCommand extends AbstractServiceCommand<PlayingCharacter> {
    private final Long id;

    public GetPlayingCharacterCommand(Long id) {
        this.id = id;
    }

    @Override
    public PlayingCharacter execute() {
        return getDaoFactory().getPlayingCharacterDao().findByPrimaryKey(id);
    }

    
}
