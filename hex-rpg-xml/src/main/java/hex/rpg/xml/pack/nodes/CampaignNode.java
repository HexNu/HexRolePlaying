package hex.rpg.xml.pack.nodes;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class CampaignNode extends AbstractRpgNode<Campaign> {

    public CampaignNode(Campaign campaign) {
        super(campaign);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.CAMPAIGN);
        Campaign campaign = (Campaign) entity();
        XmlNode typeNode = HexRpgNode.CAMPAIGN_TYPE.getXmlNode(campaign.getType().getFlavour());
        typeNode.addAttribute("code", campaign.getType().name());
        typeNode.addAttribute("type", campaign.getType().getLabel());
        result.addChild(typeNode);
        XmlNode storiesNode = HexRpgNode.STORIES.getXmlNode();
        campaign.getStories().stream().forEach((story) -> {
            storiesNode.addChild(new StoryNode(story).getXmlNode());
        });
        result.addChild(storiesNode);
        return result;
    }

}
