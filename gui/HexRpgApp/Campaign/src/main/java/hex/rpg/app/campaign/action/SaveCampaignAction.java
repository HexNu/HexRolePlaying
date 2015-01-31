package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.app.client.resource.CampaignClient;
import hex.rpg.core.domain.campaign.Campaign;

/**
 *
 * @author hln
 */
public class SaveCampaignAction extends HexAction {
    private final Campaign campaign;

    public SaveCampaignAction(Campaign campaign) {
        super("Save Campaign");
        this.campaign = campaign;
    }

    @Override
    public void performAction(Object... params) {
        CampaignClient client = new CampaignClient();
        client.saveCampaign(campaign);
    }

}
