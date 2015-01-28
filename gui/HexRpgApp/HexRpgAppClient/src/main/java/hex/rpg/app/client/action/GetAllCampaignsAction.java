package hex.rpg.app.client.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class GetAllCampaignsAction extends HexAction {

    private final List<Campaign> campaigns = new ArrayList<>();

    @Override
    public void performAction(Object... params) {
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }
}
