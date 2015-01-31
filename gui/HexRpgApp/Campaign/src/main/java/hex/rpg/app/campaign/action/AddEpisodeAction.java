package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.HexWorker;
import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import hex.rpg.api.modulesuport.gui.dialog.TextInputDialog;
import hex.rpg.app.campaign.node.StoryNode;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.core.domain.story.Episode;

/**
 *
 * @author hln
 */
public class AddEpisodeAction extends HexAction {

    private final StoryNode node;

    public AddEpisodeAction(StoryNode node) {
        super("Add Episode");
        this.node = node;
    }

    @Override
    public void performAction(Object... params) {
        TextInputDialog titleDialog = HexDialog.showTextInputDialog("Add Episode", "Episode Title", "");
        if (titleDialog.getResult().equals(HexDialog.Result.OK)) {
            final String title = titleDialog.getText();
            new HexWorker<Void>("Adding Episode") {

                @Override
                protected Void executeWork() {
                    Episode episode = new AppEpisode();
                    episode.setTitle(title);
                    episode.setStory(node.getEntity());
                    node.getEntity().addEpisode(episode);
                    return null;
                }

                @Override
                protected void updateGui() {
                    node.fireNodeChange();
                }

            }.execute();
        }
    }
}
