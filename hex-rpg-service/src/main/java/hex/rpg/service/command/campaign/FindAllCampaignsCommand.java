package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.AbstractServiceCommand;
import java.util.List;

/**
 *
 * @author hln
 */
public class FindAllCampaignsCommand extends AbstractServiceCommand<List<Campaign>> {

    @Override
    public List<Campaign> execute() {
        return getDaoFactory().getCampaignDao().findAll();
    }
}
