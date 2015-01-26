package hex.rpg.app.domain.campaign;

import hex.rpg.app.domain.AbstractSupplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;

/**
 *
 * @author hln
 */
public class AppCampaignSupplement extends AbstractSupplement implements CampaignSupplement {

    private Campaign campaign;

    @Override
    protected String getOwnerClassName() {
        return "Campaign";
    }

    @Override
    public Long getParentId() {
        return campaign != null ? campaign.getId() : null;
    }

    @Override
    public Campaign getCampaign() {
        return campaign;
    }

    @Override
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
