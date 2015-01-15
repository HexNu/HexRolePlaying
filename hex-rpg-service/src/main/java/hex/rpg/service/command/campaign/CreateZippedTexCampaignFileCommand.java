package hex.rpg.service.command.campaign;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.cft.CreateZippedTexStream;
import hex.rpg.service.command.AbstractServiceCommand;
import java.io.InputStream;

/**
 *
 * @author hln
 */
public class CreateZippedTexCampaignFileCommand extends AbstractServiceCommand<InputStream> {

    private final Campaign campaign;

    public CreateZippedTexCampaignFileCommand(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public InputStream execute() {
        CreateZippedTexStream createZippedTexStream = new CreateZippedTexStream(campaign);
        return createZippedTexStream.execute();
    }
}
