package hex.rpg.jpa.domain.character.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.character.PlayerNote;
import hex.rpg.core.domain.character.PlayingCharacter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "PlayerNote")
public class RpgPlayerNote implements PlayerNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = RpgPlayingCharacter.class)
    private PlayingCharacter character;
    @Column
    private String label;
    @Temporal(TemporalType.DATE)
    private Date noteDate;
    @Column(length = 2 * Constants.KB)
    private String text;
    private static final int SHORT_DESCRIPTION_PREFFERED_LENGTH = 50;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return getCharacter().getId();
    }

    @Override
    public String getName() {
        return getLabel();
    }

    @Override
    public String getLabel() {
        return label != null ? label : "Untitled Note";
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public Date getDate() {
        return noteDate;
    }

    @Override
    public void setDate(Date date) {
        this.noteDate = date;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(PlayerNote otherNote) {
        if (otherNote == null) {
            return 1;
        }
        return this.getDate().compareTo(otherNote.getDate());
    }

    @Override
    public PlayingCharacter getCharacter() {
        return character;
    }


    @Override
    public void setPlayingCharacter(PlayingCharacter character) {
        this.character = character;
    }

    @Override
    public String getShortDescription() {
        if (text != null) {
            if (text.length() > 50 && text.substring(SHORT_DESCRIPTION_PREFFERED_LENGTH).contains(" ")) {
                return text.substring(SHORT_DESCRIPTION_PREFFERED_LENGTH, text.indexOf(" ")) + "...";
            } else {
                return text;
            }
        }
        return null;
    }
}
