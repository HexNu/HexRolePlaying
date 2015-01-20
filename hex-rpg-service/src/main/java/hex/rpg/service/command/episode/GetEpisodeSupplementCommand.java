package hex.rpg.service.command.episode;

import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetEpisodeSupplementCommand extends AbstractServiceCommand<EpisodeSupplement> {

    private final Long id;

    public GetEpisodeSupplementCommand(Long id) {
        this.id = id;
    }

    @Override
    public EpisodeSupplement execute() {
        return getDaoFactory().getEpisodeSupplementDao().findByPrimaryKey(id);
    }

}
