package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class GetCampaignCommand extends AbstractServiceCommand<Campaign> {

    private final Long id;

    public GetCampaignCommand(Long id) {
        this.id = id;
    }

    @Override
    public Campaign execute() {
        return getDaoFactory().getCampaignDao().findByPrimaryKey(id);
    }
}
