package hex.rpg.core.domain.character;

import hex.rpg.core.domain.CharacterEntity;
import java.util.List;

/**
 *
 * @author hln
 */
public interface PlayingCharacter extends CharacterEntity {

    String getPlayerAlias();

    void setPlayerAlias(String alias);

    String getPlayerName();

    void setPlayerName(String name);

    List<PlayerNote> getNotes();

    void addNote(PlayerNote note);
    
    boolean hasNotes();
}
