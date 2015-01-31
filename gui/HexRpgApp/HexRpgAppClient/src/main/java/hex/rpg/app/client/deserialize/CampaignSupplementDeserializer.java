package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.dto.in.CreateSupplementDTO;

/**
 *
 * @author hln
 */
public class CampaignSupplementDeserializer extends AbstractSupplementDeserializer<AppCampaignSupplement> {

    public CampaignSupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new AppCampaignSupplement());
    }
}
