package hex.rpg.core.domain.character;

import hex.rpg.core.domain.DomainEntity;
import java.util.Date;

/**
 *
 * @author hln
 */
public interface PlayerNote extends DomainEntity, Comparable<PlayerNote> {

    PlayingCharacter getCharacter();
    
    void setPlayingCharacter(PlayingCharacter character);
    
    String getLabel();
    
    void setLabel(String label);
    
    Date getDate();
    
    void setDate(Date date);
    
    String getText();
   
    void setText(String text);
}
