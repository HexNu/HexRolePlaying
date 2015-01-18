package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.campaign.Campaign;

/**
 *
 * @author hln
 */
public class CampaignCarrier extends AbstractEntityCarrier<Campaign> {

    public CampaignCarrier(Long tempId, Long tempParentId, Campaign entity) {
        super(tempId, tempParentId, entity);
    }

}
