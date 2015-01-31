package hex.rpg.dto.deserializer;

import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaignSupplement;

/**
 *
 * @author hln
 */
public class CampaignSupplementDeserializer extends AbstractSupplementDeserializer<RpgCampaignSupplement> {

    public CampaignSupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new RpgCampaignSupplement());
    }
}
