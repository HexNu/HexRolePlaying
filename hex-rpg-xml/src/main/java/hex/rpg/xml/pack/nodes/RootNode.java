package hex.rpg.xml.pack.nodes;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import java.util.ArrayList;
import java.util.List;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class RootNode extends AbstractRpgNode<List<Campaign>> {

    private final List<Campaign> campaigns = new ArrayList<>();

    public RootNode(Campaign campaign) {
        this.campaigns.add(campaign);
    }

    public RootNode(List<Campaign> campaigns) {
        this.campaigns.addAll(campaigns);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = HexRpgNode.CFX.getXmlNode();
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
