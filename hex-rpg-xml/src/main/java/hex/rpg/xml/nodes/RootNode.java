package hex.rpg.xml.nodes;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.xml.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import java.util.List;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class RootNode extends AbstractRpgNode<List<Campaign>> {

    private final List<Campaign> campaigns;

    public RootNode(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = HexRpgNode.CFF.getXmlNode();
        result.addAttribute("version", "0.1");
        XmlNode infoNode = HexRpgNode.INFORMATION.getXmlNode();
        infoNode.addChild(HexRpgNode.TITLE.getXmlNode("HexRpg"));
        infoNode.addChild(HexRpgNode.DESCRIPTION.getXmlNode("HexRpg is a project aimed at keeping track on and planning gaming campaigns."));
        result.addChild(infoNode);
        campaigns.stream().forEach((campaign) -> {
            result.addChild(new CampaignNode(campaign).getXmlNode());
        });
        return result;
    }
}
