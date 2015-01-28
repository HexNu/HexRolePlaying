package hex.rpg.app.client.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.app.client.resource.CampaignClient;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class GetAllCampaignsAction extends HexAction {

    private List<Campaign> campaigns = new ArrayList<>();

    @Override
    public void performAction(Object... params) {
        campaigns = new CampaignClient().getAllCampaigns();
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }
}
