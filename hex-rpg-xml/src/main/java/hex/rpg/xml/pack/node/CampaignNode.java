package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class CampaignNode extends AbstractRpgNode<Campaign> {

    public CampaignNode(Campaign campaign, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(campaign, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.CAMPAIGN);
        Campaign campaign = (Campaign) narrativeEntity();
        XmlNode typeNode = HexRpgNode.CAMPAIGN_TYPE.getXmlNode(campaign.getType().getFlavour());
        typeNode.addAttribute("code", campaign.getType().name());
        typeNode.addAttribute("label", campaign.getType().getLabel());
        result.addChild(typeNode);
        XmlNode storiesNode = HexRpgNode.STORIES.getXmlNode();
        campaign.getStories().stream().forEach((story) -> {
            storiesNode.addChild(new StoryNode(story, isTexFormated(), getNames(), getPlaces(), getCreatures()).getXmlNode());
        });
        result.addChild(storiesNode);
        XmlNode charactersNode = HexRpgNode.NON_PLAYING_CHARACTERS.getXmlNode();
        campaign.getCharacters().stream().forEach((character) -> {
           charactersNode.addChild(new NonPlayingCharacterNode(character, isTexFormated(), getNames(), getPlaces(), getCreatures()).getXmlNode());
        });
        result.addChild(charactersNode);
        return result;
    }

}
