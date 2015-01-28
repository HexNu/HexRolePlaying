package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import hex.rpg.api.modulesuport.gui.dialog.TextInputDialog;
import hex.rpg.app.campaign.lookup.CampaignViewContext;
import hex.rpg.app.client.action.GetFullCampaignAction;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.core.domain.campaign.Campaign;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "hex.rpg.app.campaign.action.GetCampaignAction"
)
@ActionRegistration(
        iconBase = "hex/rpg/app/campaign/action/C.png",
        displayName = "#CTL_GetCampaignAction"
)
@ActionReference(path = "Menu/File", position = 1300)
@Messages("CTL_GetCampaignAction=Get Campaign")
public final class GetCampaignAction extends HexAction {

    private AppCampaign campaign = null;

    public GetCampaignAction() {
        super("Hämta kampanj");
    }

    @Override
    public void performAction(Object... params) {
        Long id = null;
        if (params.length > 0 && params[0] instanceof Long) {
            id = (Long) params[0];
        }
        if (id == null) {
            TextInputDialog dialog = HexDialog.showTextInputDialog("Set campaign-id", "1");
            if (dialog.getResult().equals(HexDialog.Result.OK)) {
                id = Long.valueOf(dialog.getText());
            }
        }
        campaign = new GetFullCampaignAction().getCampaign(id);
        CampaignViewContext.publishCampaign(campaign);
    }

    public Campaign getCampaign() {
        return campaign;
    }
}
