package hex.rpg.xml.pack.nodes;

import hex.rpg.core.domain.Supplement;
import hex.rpg.xml.pack.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class SupplementNode extends AbstractRpgNode<Supplement> {

    public SupplementNode(Supplement supplement) {
        super(supplement);
    }

    @Override
    public XmlNode getXmlNode() {
        Supplement supplement = (Supplement) entity();
        XmlNode node = buildNode(HexRpgNode.SUPPLEMENT);
        node.addAttribute("file-path", supplement.createPath());
        node.addAttribute("type", supplement.getType().name().toLowerCase());
        node.addAttribute("media-type", supplement.getMediaType());
        node.addAttribute("size", supplement.getSize());
        return node;
    }
}
