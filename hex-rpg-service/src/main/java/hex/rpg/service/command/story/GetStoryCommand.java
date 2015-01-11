package hex.rpg.service.command.story;

import hex.rpg.core.domain.story.Story;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetStoryCommand extends AbstractServiceCommand<Story>{
    private final Long id;

    public GetStoryCommand(Long id) {
        this.id = id;
    }

    
    @Override
    public Story execute() {
        return getDaoFactory().getStoryDao().findByPrimaryKey(id);
    }
}
