package hex.rpg.xml.nodes;

import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Story;
import hex.rpg.xml.AbstractRpgNode;
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
        XmlNode typeNode = HexRpgNode.CAMPAIGN_TYPE.getXmlNode(campaign.getType().getLabel());
        typeNode.addAttribute("code", campaign.getType().name());
        typeNode.addAttribute("super", campaign.getType().getSuperType());
        result.addChild(typeNode);
        campaign.getStories().stream().forEach((story) -> {
            result.addChild(new StoryNode(story).getXmlNode());
        });
        return result;
    }

}
