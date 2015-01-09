package hex.rpg.xml.nodes;

import hex.rpg.core.domain.Supplement;
import hex.rpg.xml.AbstractRpgNode;
import hex.rpg.xml.HexRpgNode;
import se.digitman.lightxml.XmlNode;
import se.digitman.util.Base64Coder;

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
        XmlNode node = buildNode(HexRpgNode.SUPPLEMENT);
        Supplement supplement = (Supplement) entity();
        node.addText(Base64Coder.encodeLines(supplement.getContentAsByteArray()));
        node.addAttribute("type", supplement.getType().name().toLowerCase());
        node.addAttribute("media-type", supplement.getMediaType());
        node.addAttribute("size", supplement.getSize());
        return node;
    }

}
