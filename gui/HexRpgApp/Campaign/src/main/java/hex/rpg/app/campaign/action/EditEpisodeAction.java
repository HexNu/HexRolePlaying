package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.DataEditorTopComponent;
import hex.rpg.app.campaign.gui.EpisodeEditorTopComponent;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.core.domain.story.Episode;
import org.openide.windows.TopComponent;
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
        boolean isAlreadyOpened = false;
        TopComponent[] openedTopComponents = WindowManager.getDefault().getOpenedTopComponents(WindowManager.getDefault().findMode("editor"));
        if (openedTopComponents != null) {
            for (TopComponent tc : openedTopComponents) {
                if (tc instanceof DataEditorTopComponent) {
                    if (episode.getTitle().equals(((DataEditorTopComponent) tc).toString())) {
                        isAlreadyOpened = true;
                        tc.requestFocus();
                        tc.requestActive();
                        break;
                    }
                }
            }
        }
        if (!isAlreadyOpened) {
            EpisodeEditorTopComponent episodeEditor = new EpisodeEditorTopComponent(episode);
            episodeEditor.open();
        }
//        EpisodeEditorTopComponent episodeEditor = (EpisodeEditorTopComponent) WindowManager.getDefault().findTopComponent(EpisodeEditorTopComponent.PREFERRED_ID);
    }
}
