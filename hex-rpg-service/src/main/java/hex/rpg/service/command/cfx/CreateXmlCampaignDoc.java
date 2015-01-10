package hex.rpg.service.command.cfx;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.AbstractServiceCommand;
import hex.rpg.xml.pack.HexRpgDocument;
import hex.rpg.xml.pack.nodes.RootNode;
import java.util.ArrayList;
import java.util.List;
import se.digitman.lightxml.XmlDocument;

/**
 *
 * @author hln
 */
public class CreateXmlCampaignDoc extends AbstractServiceCommand<XmlDocument> {

    private final List<Campaign> campaigns = new ArrayList<>();

    public CreateXmlCampaignDoc(Campaign campaign) {
        this.campaigns.add(campaign);
    }
    
    

    public CreateXmlCampaignDoc(List<Campaign> campaigns) {
        this.campaigns.addAll(campaigns);
    }

    @Override
    public XmlDocument execute() {
        return new HexRpgDocument(new RootNode(campaigns).getXmlNode()).get();
    }

}
