package hex.rpg.service.command.cfx;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.service.command.AbstractServiceCommand;
import hex.rpg.xml.pack.HexRpgDocument;
import hex.rpg.xml.pack.node.RootNode;
import java.util.ArrayList;
import java.util.List;
import se.digitman.lightxml.XmlDocument;

/**
 *
 * @author hln
 */
public class CreateCfxDoc extends AbstractServiceCommand<XmlDocument> {

    private final List<Campaign> campaigns = new ArrayList<>();

    public CreateCfxDoc(List<Campaign> campaigns, List<PlayingCharacter> playingCharacters) {
        this.campaigns.addAll(campaigns);
    }

    @Override
    public XmlDocument execute() {
        RootNode rootNode = new RootNode();
        rootNode.addCampaigns(campaigns);
        return new HexRpgDocument(rootNode).get();
    }
}
