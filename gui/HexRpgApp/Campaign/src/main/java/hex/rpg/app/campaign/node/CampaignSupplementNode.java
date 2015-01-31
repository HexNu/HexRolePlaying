package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.EditCampaignSupplementAction;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;

/**
 *
 * @author hln
 */
public class CampaignSupplementNode extends AbstractRpgNode<AppCampaignSupplement> {

    public CampaignSupplementNode(AppCampaignSupplement entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        addAction(new EditCampaignSupplementAction(getEntity()));
    }

}
