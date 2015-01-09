package hex.rpg.core.domain.campaign;

import hex.rpg.core.domain.Supplement;

/**
 *
 * @author hln
 */
public interface CampaignSupplement extends Supplement {

    Campaign getCampaign();

    void setCampaign(Campaign campaign);
}
