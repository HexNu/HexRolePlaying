package hex.rpg.service.command.character;

import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;
import java.util.List;

/**
 *
 * @author hln
 */
public class FindAllPlayingCharactersCommand extends AbstractServiceCommand<List<PlayingCharacter>> {

    @Override
    public List<PlayingCharacter> execute() {
        return getDaoFactory().getPlayingCharacterDao().findAll();
    }
}
