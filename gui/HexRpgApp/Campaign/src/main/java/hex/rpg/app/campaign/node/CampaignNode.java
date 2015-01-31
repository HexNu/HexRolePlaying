package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.EditCampaignAction;
import hex.rpg.app.domain.campaign.AppCampaign;

/**
 *
 * @author hln
 */
public class CampaignNode extends AbstractRpgNode<AppCampaign> {

    public CampaignNode(AppCampaign entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        addAction(new EditCampaignAction(getEntity()));
    }
}
