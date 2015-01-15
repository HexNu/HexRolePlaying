package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.xml.HexRpgNode;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class RootNode {

    private final boolean texFormated;
    private final List<Campaign> campaigns = new ArrayList<>();
    private final List<PlayingCharacter> playingCharacters = new ArrayList<>();
    private final Set<String> names = new LinkedHashSet<>();
    private final Set<String> places = new LinkedHashSet<>();
    private final Set<String> creatures = new LinkedHashSet<>();

    public RootNode() {
        this(false);
    }

    public RootNode(boolean texFormated) {
        this.texFormated = texFormated;
    }

    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
    }

    public void addCampaigns(List<Campaign> campaigns) {
        campaigns.addAll(campaigns);
    }

    public void addPlayingCharacter(PlayingCharacter character) {
        playingCharacters.add(character);
    }

    public void addPlayingCharacters(List<PlayingCharacter> characters) {
        playingCharacters.addAll(characters);
    }

    public XmlNode getXmlNode() {
        XmlNode result = HexRpgNode.CFX.getXmlNode();
        result.addAttribute("version", "0.1");
        XmlNode infoNode = HexRpgNode.INFORMATION.getXmlNode();
        infoNode.addChild(HexRpgNode.TITLE.getXmlNode("HexRpg"));
        infoNode.addChild(HexRpgNode.DESCRIPTION.getXmlNode("HexRpg is a project aimed at keeping track on and planning gaming campaigns."));
        result.addChild(infoNode);
        campaigns.stream().map((campaign) -> {
            XmlNode campaignNode = new CampaignNode(campaign, texFormated, names, places, creatures).getXmlNode();
            XmlNode nonPlayingCharactersNode = HexRpgNode.NON_PLAYING_CHARACTERS.getXmlNode();
            campaign.getCharacters().stream().forEach((character) -> {
                nonPlayingCharactersNode.addChild(new NonPlayingCharacterNode(character, texFormated, names, places, creatures).getXmlNode());
            });
            campaignNode.addChild(nonPlayingCharactersNode);
            return campaignNode;
        }).forEach((campaignNode) -> {
            result.addChild(campaignNode);
        });
        if (playingCharacters != null && !playingCharacters.isEmpty()) {
            XmlNode playingCharactersNode = HexRpgNode.PLAYING_CHARACTERS.getXmlNode();
            playingCharacters.stream().forEach((character) -> {
                playingCharactersNode.addChild(new PlayingCharacterNode(character, texFormated, names, places, creatures).getXmlNode());
            });
            result.addChild(playingCharactersNode);
        }
        return result;
    }

    public Set<String> getNames() {
        return names;
    }

    public Set<String> getPlaces() {
        return places;
    }

    public Set<String> getCreatures() {
        return creatures;
    }
}
