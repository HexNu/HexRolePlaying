package hex.rpg.app.client.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.app.client.resource.CampaignClient;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.core.domain.campaign.Campaign;

/**
 *
 * @author hln
 */
public class GetFullCampaignAction extends HexAction {
    
    private Campaign result = new AppCampaign();

    @Override
    public void performAction(Object... params) {
        if (params.length > 0 && params[0] instanceof Long) {
            Long id = (Long) params[0];
            CampaignClient campaignClient = new CampaignClient();
            result = campaignClient.getCampaign(id);
        }
    }

    public Campaign getCampaign(Long id) {
        performAction(id);
        return result;
    }
}
