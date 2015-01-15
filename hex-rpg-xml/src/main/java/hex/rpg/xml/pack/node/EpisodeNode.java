package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class EpisodeNode extends AbstractRpgNode<Episode> {

    public EpisodeNode(Episode episode, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(episode, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.EPISODE);
        Episode episode = (Episode) narrativeEntity();
        result.addAttribute("index", episode.getIndex());
        String content = episode.getContent();
        if (isTexFormated()) {
            content = parseNode(content);
        }
        result.addChild(HexRpgNode.CONTENT.getXmlNode(content));
        return result;
    }

}
