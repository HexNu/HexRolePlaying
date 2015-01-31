package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.EditEpisodeSupplementAction;
import hex.rpg.app.domain.story.AppEpisodeSupplement;

/**
 *
 * @author hln
 */
public class EpisodeSupplementNode extends AbstractRpgNode<AppEpisodeSupplement> {

    public EpisodeSupplementNode(AppEpisodeSupplement entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        addAction(new EditEpisodeSupplementAction(getEntity()));
    }

}
