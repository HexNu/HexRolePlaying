package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.PlayingCharacterSupplement;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetPlayingCharacterSupplementCommand extends AbstractServiceCommand<PlayingCharacterSupplement> {
    private final Long id;

    public GetPlayingCharacterSupplementCommand(Long id) {
        this.id = id;
    }

    @Override
    public PlayingCharacterSupplement execute() {
        return getDaoFactory().getPlayingCharacterSupplementDao().findByPrimaryKey(id);
    }

}
