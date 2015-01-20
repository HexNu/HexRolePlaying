package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetNonPlayingCharacterSupplementCommand extends AbstractServiceCommand<NonPlayingCharacterSupplement> {
    private final Long id;

    public GetNonPlayingCharacterSupplementCommand(Long id) {
        this.id = id;
    }

    @Override
    public NonPlayingCharacterSupplement execute() {
        return getDaoFactory().getNonPlayingCharacterSupplementDao().findByPrimaryKey(id);
    }

}
