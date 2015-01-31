package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.HexWorker;
import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import hex.rpg.api.modulesuport.gui.dialog.TextInputDialog;
import hex.rpg.app.campaign.node.CampaignNode;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.core.domain.story.Story;

/**
 *
 * @author hln
 */
public class AddStoryAction extends HexAction {

    private final CampaignNode node;

    public AddStoryAction(CampaignNode node) {
        super("Add Story");
        this.node = node;
    }

    @Override
    public void performAction(Object... params) {
        TextInputDialog titleDialog = HexDialog.showTextInputDialog("Add Story", "Story Title", "");
        if (titleDialog.getResult().equals(HexDialog.Result.OK)) {
            final String title = titleDialog.getText();
            new HexWorker<Void>("Adding Story") {

                @Override
                protected Void executeWork() {
                    Story story = new AppStory();
                    story.setTitle(title);
                    story.setCampaign(node.getEntity());
                    node.getEntity().addStory(story);
                    story.setIndex(node.getEntity().getStories().size());
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
