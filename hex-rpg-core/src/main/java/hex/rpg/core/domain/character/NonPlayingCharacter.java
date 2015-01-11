package hex.rpg.core.domain.character;

import hex.rpg.core.domain.CharacterEntity;

/**
 *
 * @author hln
 */
public interface NonPlayingCharacter extends CharacterEntity {

    String getRefereeInfo();

    void setRefereeInfo(String refereeInfo);

    String getRefereeNotes();

    void setRefereeNotes(String refereeNotes);
}
