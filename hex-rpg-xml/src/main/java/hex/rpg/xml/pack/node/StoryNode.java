package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.story.Story;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class StoryNode extends AbstractRpgNode<Story> {

    public StoryNode(Story story, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(story, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.STORY);
        Story story = (Story) narrativeEntity();
        result.addAttribute("index", story.getIndex());
        XmlNode episodesNode = HexRpgNode.EPISODES.getXmlNode();
        story.getEpisodes().stream().forEach((episode) -> {
            episodesNode.addChild(new EpisodeNode(episode, isTexFormated(), getNames(), getPlaces(), getCreatures()).getXmlNode());
        });
        result.addChild(episodesNode);
        return result;
    }

}
