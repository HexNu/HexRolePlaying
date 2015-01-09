package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.AbstractServiceCommand;

/**
 *
 * @author hln
 */
public class SaveCampaignCommand extends AbstractServiceCommand<Campaign> {

    private final Campaign campaign;

    public SaveCampaignCommand(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public Campaign execute() {
        return getDaoFactory().getCampaignDao().save(campaign);
    }

}
