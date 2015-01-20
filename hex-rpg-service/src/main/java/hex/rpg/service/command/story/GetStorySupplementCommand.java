package hex.rpg.service.command.story;

import hex.rpg.core.domain.story.StorySupplement;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetStorySupplementCommand extends AbstractServiceCommand<StorySupplement> {

    private final Long id;

    public GetStorySupplementCommand(Long id) {
        this.id = id;
    }

    @Override
    public StorySupplement execute() {
        return getDaoFactory().getStorySupplementDao().findByPrimaryKey(id);
    }
}
