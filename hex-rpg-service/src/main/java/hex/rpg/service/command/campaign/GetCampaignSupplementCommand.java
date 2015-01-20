package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetCampaignSupplementCommand extends AbstractServiceCommand<CampaignSupplement> {
    private final Long id;

    public GetCampaignSupplementCommand(Long id) {
        this.id = id;
    }

    @Override
    public CampaignSupplement execute() {
        return getDaoFactory().getCampaignSupplementDao().findByPrimaryKey(id);
    }

}
