package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.AddSupplementAction;
import hex.rpg.app.campaign.action.EditEpisodeAction;
import hex.rpg.app.domain.story.AppEpisode;

/**
 *
 * @author hln
 */
public class EpisodeNode extends AbstractRpgNode<AppEpisode> {

    public EpisodeNode(AppEpisode entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        addAction(new EditEpisodeAction(getEntity()));
        addAction(new AddSupplementAction(this));
    }
}
