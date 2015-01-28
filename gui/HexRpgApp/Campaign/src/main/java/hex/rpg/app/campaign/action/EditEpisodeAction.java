package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.app.campaign.gui.EpisodeEditorTopComponent;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.core.domain.story.Episode;
import org.openide.windows.WindowManager;

/**
 *
 * @author hln
 */
public class EditEpisodeAction extends HexAction {

    private final Episode episode;
    
    public EditEpisodeAction() {
        this(new AppEpisode());
    }

    public EditEpisodeAction(Episode episode) {
        super("Edit episode");
        this.episode = episode;
    }

    @Override
    public void performAction(Object... params) {
        EpisodeEditorTopComponent episodeEditor = (EpisodeEditorTopComponent) WindowManager.getDefault().findTopComponent(EpisodeEditorTopComponent.PREFERRED_ID);
        if (episodeEditor != null) {
            episodeEditor.open();
            episodeEditor.setEpisode(episode);
        }
    }
}
