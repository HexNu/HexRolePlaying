package hex.rpg.xml.nodes;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.xml.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class StoryNode extends AbstractRpgNode<Story> {

    public StoryNode(Story story) {
        super(story);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.STORY);
        Story story = (Story) entity();
        result.addAttribute("index", story.getIndex());
        XmlNode episodesNode = HexRpgNode.EPISODES.getXmlNode();
        story.getEpisodes().stream().forEach((episode) -> {
            episodesNode.addChild(new EpisodeNode(episode).getXmlNode());
        });
        result.addChild(episodesNode);
        return result;
    }

}
