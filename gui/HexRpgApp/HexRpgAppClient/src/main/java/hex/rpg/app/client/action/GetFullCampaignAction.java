package hex.rpg.app.client.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.app.client.resource.CampaignClient;
import hex.rpg.app.domain.campaign.AppCampaign;

/**
 *
 * @author hln
 */
public class GetFullCampaignAction extends HexAction {
    
    private AppCampaign result = new AppCampaign();

    @Override
    public void performAction(Object... params) {
        if (params.length > 0 && params[0] instanceof Long) {
            Long id = (Long) params[0];
            CampaignClient campaignClient = new CampaignClient();
            result = (AppCampaign) campaignClient.getCampaign(id);
        }
    }

    public AppCampaign getCampaign(Long id) {
        performAction(id);
        return result;
    }
}
