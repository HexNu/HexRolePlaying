package hex.rpg.service.command.cff;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.AbstractServiceCommand;
import hex.rpg.xml.nodes.RootNode;
import java.util.List;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class CreateXmlCampaignFile extends AbstractServiceCommand<XmlNode> {

    private List<Campaign> campaigns;

    public CreateXmlCampaignFile(Campaign campaign) {
    }

    public CreateXmlCampaignFile(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public XmlNode execute() {
        XmlNode result = new RootNode(campaigns).getXmlNode();
        return result;
    }

}
