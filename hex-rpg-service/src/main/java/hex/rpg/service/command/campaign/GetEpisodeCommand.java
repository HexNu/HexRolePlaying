package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetEpisodeCommand extends AbstractServiceCommand<Episode>{
    private final Long id;

    public GetEpisodeCommand(Long id) {
        this.id = id;
    }

    
    @Override
    public Episode execute() {
        return getDaoFactory().getEpisodeDao().findByPrimaryKey(id);
    }
}
