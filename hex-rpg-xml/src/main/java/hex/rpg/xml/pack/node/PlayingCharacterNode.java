package hex.rpg.xml.pack.node;

import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.xml.HexRpgNode;
import hex.rpg.xml.pack.AbstractRpgNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class PlayingCharacterNode extends AbstractRpgNode<PlayingCharacter> {

    public PlayingCharacterNode(PlayingCharacter character, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        super(character, texFormated, names, places, creatures);
    }

    @Override
    public XmlNode getXmlNode() {
        XmlNode result = buildNode(HexRpgNode.PLAYING_CHARACTER);
        PlayingCharacter character = (PlayingCharacter) characterEntity();
        result.addChild(HexRpgNode.PLAYER_NAME.getXmlNode(character.getPlayerName()));
        result.addChild(HexRpgNode.PLAYER_ALIAS.getXmlNode(character.getPlayerAlias()));
        if (character.hasNotes()) {
            XmlNode notesNode = HexRpgNode.PLAYER_NOTES.getXmlNode();
            character.getNotes().stream().forEach((note) -> {
                notesNode.addChild(new PlayerNoteNode(note, isTexFormated(), getNames(), getPlaces(), getCreatures()).getXmlNode());
            });
            result.addChild(notesNode);
        }
        return result;
    }

}
