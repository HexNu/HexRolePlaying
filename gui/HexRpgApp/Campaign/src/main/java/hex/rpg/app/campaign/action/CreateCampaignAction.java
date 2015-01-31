package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import hex.rpg.api.modulesuport.gui.dialog.TextInputDialog;
import hex.rpg.app.domain.campaign.AppCampaign;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File", id = "hex.rpg.app.campaign.action.CreateCampaignAction")
@ActionRegistration(iconBase = "hex/rpg/app/campaign/action/library.png", displayName = "#CTL_CreateCampaignAction")
@ActionReference(path = "Menu/File", position = 1300, separatorAfter = 1350)
@Messages("CTL_CreateCampaignAction=Create campaign")
public final class CreateCampaignAction extends HexAction {

    public CreateCampaignAction() {
        super("Create campaign");
    }

    @Override
    public void performAction(Object... params) {
        AppCampaign campaign = new AppCampaign();
        TextInputDialog dialog = HexDialog.showTextInputDialog("Campaign title", "Provide title for the campaign", "");
        if (dialog.getResult().equals(HexDialog.Result.OK)) {
            campaign.setTitle(dialog.getText());
            new EditCampaignAction(campaign).performAction(params);
        }
    }
}
