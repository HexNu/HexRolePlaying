package hex.rpg.service.command.stf;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.stf.CreateZippedStfStream;
import hex.rpg.service.command.AbstractServiceCommand;
import java.io.InputStream;

/**
 *
 * @author hln
 */
public class GetSimpleTextFormatFileCommand extends AbstractServiceCommand<InputStream>{
    private final Campaign campaign;

    public GetSimpleTextFormatFileCommand(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public InputStream execute() {
        return new CreateZippedStfStream(campaign).execute();
    }
}
