package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;
import java.util.List;

/**
 *
 * @author hln
 */
public class FindAllNonPlayingCharactersCommand extends AbstractServiceCommand<List<NonPlayingCharacter>> {

    @Override
    public List<NonPlayingCharacter> execute() {
        return getDaoFactory().getNonPlayingCharacterDao().findAll();
    }
}
