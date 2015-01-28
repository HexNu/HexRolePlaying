package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.dto.in.SupplementDTO;

/**
 *
 * @author hln
 */
public class CampaignSupplementAssembler extends AbstractSupplementAssembler<AppCampaignSupplement> {

    public CampaignSupplementAssembler(SupplementDTO dto) {
        super(dto, new AppCampaignSupplement());
    }
}
