package hex.rpg.service.command.cfx;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.cfx.CreateCfxStream;
import hex.rpg.service.command.AbstractServiceCommand;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author hln
 */
public class CreateXmlCampaignInputStream extends AbstractServiceCommand<InputStream> {

    private final List<Campaign> campaigns;

    public CreateXmlCampaignInputStream(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public InputStream execute() {
        return new CreateCfxStream(campaigns).execute();
    }
}
