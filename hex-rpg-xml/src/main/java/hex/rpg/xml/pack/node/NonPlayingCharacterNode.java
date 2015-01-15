package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.xml.HexRpgNode;
import hex.rpg.xml.pack.AbstractRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterNode extends AbstractRpgNode<NonPlayingCharacter> {

    public NonPlayingCharacterNode(NonPlayingCharacter character, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(character, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.NON_PLAYING_CHARACTER);
        NonPlayingCharacter character = (NonPlayingCharacter) characterEntity();
        String refereeInfo = character.getRefereeInfo();
        String refereeNotes = character.getRefereeNotes();
        if (isTexFormated()) {
            refereeInfo = parseNode(refereeInfo);
            refereeNotes = parseNode(refereeNotes);
        }
        result.addChild(HexRpgNode.REFEREE_INFO.getXmlNode(refereeInfo));
        result.addChild(HexRpgNode.REFEREE_NOTES.getXmlNode(refereeNotes));
        return result;
    }
}
