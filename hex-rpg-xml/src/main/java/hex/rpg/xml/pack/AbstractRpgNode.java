package hex.rpg.xml.pack;

import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.xml.HexRpgNode;
import hex.rpg.xml.pack.nodes.SupplementNode;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 * @param <T>
 */
public abstract class AbstractRpgNode<T> {

    private final T entity;

    protected AbstractRpgNode(T entity) {
        this.entity = entity;
    }
    
    protected AbstractRpgNode() {
        this(null);
    }

    protected NarrativeEntity entity() {
        return entity instanceof NarrativeEntity ? (NarrativeEntity) entity : null;
    }

    public abstract XmlNode getXmlNode();

    protected XmlNode buildNode(HexRpgNode rpgode) {
        XmlNode node = rpgode.getXmlNode();
        if (entity() != null) {
            node.addChild(HexRpgNode.TITLE.getXmlNode(entity().getTitle()));
            node.addChild(HexRpgNode.SHORT_DESCRIPTION.getXmlNode(entity().getShortDescription()));
            node.addChild(HexRpgNode.REFEREE_NOTES.getXmlNode(entity().getRefereeNotes()));
            node.addChild(HexRpgNode.DESCRIPTION.getXmlNode(entity().getDescription()));
            node.addChild(HexRpgNode.REFEREE_INFO.getXmlNode(entity().getRefereeInfo()));
            if (entity().hasSupplements()) {
                XmlNode supplementsNode = HexRpgNode.SUPPLEMENTS.getXmlNode();
                entity().getSupplements().stream().forEach((supplement) -> {
                    supplementsNode.addChild(new SupplementNode(supplement).getXmlNode());
                });
                node.addChild(supplementsNode);
            }
        }
        return node;

    }
}
