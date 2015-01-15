package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.character.PlayerNote;
import hex.rpg.xml.HexRpgNode;
import hex.rpg.xml.pack.AbstractRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class PlayerNoteNode extends AbstractRpgNode<PlayerNote> {

    public PlayerNoteNode(PlayerNote note, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(note, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.PLAYER_NOTE);
        PlayerNote note = (PlayerNote) genericEntity();
        result.addChild(HexRpgNode.LABEL.getXmlNode(note.getLabel()));
        result.addChild(HexRpgNode.DATE.getXmlNode(note.getDate()));
        String text = note.getText();
        if (isTexFormated()) {
            text = parseNode(text);
        }
        result.addChild(HexRpgNode.TEXT.getXmlNode(text));
        return result;
    }

}
