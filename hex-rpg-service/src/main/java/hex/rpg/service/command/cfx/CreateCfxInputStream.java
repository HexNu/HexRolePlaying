package hex.rpg.service.command.cfx;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.io.cfx.CreateCfxStream;
import hex.rpg.service.command.AbstractServiceCommand;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author hln
 */
public class CreateCfxInputStream extends AbstractServiceCommand<InputStream> {

    private final Campaign campaign;
    private final List<PlayingCharacter> playingCharacters;

    public CreateCfxInputStream(Campaign campaign, List<PlayingCharacter> playingCharacters) {
        this.campaign = campaign;
        this.playingCharacters = playingCharacters;
    }

    @Override
    public InputStream execute() {
        return new CreateCfxStream(campaign, playingCharacters).execute();
    }
}
