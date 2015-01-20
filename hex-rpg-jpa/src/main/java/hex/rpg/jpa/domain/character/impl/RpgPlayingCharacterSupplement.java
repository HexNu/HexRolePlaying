package hex.rpg.jpa.domain.character.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.core.domain.character.PlayingCharacterSupplement;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "PlayingCharacterSupplement")
public class RpgPlayingCharacterSupplement implements PlayingCharacterSupplement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column(length = 32 * Constants.KB)
    private String refereeInfo;
    @Column
    private Integer supplementIndex;
    @Column(length = Constants.KB)
    private String shortDescription;
    @Column(length = 32 * Constants.KB)
    private String description;
    @Column(length = 2 * Constants.KB)
    private String refereeNotes;
    @Column
    private String mediaType;
    @Lob
    @Column(length = 64 * Constants.MB)
    private byte[] content;
    @ManyToOne(targetEntity = RpgPlayingCharacter.class)
    private PlayingCharacter character;
    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return getPlayingCharacter().getId();
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getIndex() {
        return supplementIndex;
    }

    @Override
    public void setIndex(Integer index) {
        this.supplementIndex = index;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        this.refereeInfo = refereeInfo;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        this.refereeNotes = refereeNotes;
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public InputStream getContent() {
        return new ByteArrayInputStream(content);
    }

    @Override
    public byte[] getContentAsByteArray() {
        return content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public Integer getSize() {
        return content.length;
    }

    @Override
    public List<Supplement> getSupplements() {
        throw new UnsupportedOperationException("N/A");
    }

    @Override
    public void addSupplement(Supplement supplement) {
        throw new UnsupportedOperationException("N/A");
    }

    @Override
    public PlayingCharacter getPlayingCharacter() {
        return character;
    }

    @Override
    public void setPlayingCharacter(PlayingCharacter character) {
        this.character = character;
    }

    @Override
    public String createPath() {
        String result = BASE_PATH + "PlayingCharacter/supplement-" + id;
        if (getFileExtension() != null) {
            result += "." + getFileExtension();
        }
        return result;
    }

    private String getFileExtension() {
        if (mediaType.startsWith("text")) {
            return "txt";
        } else if (mediaType.startsWith("image")) {
            return mediaType.substring(mediaType.lastIndexOf("/") + 1);
        } else if (mediaType.contains("+")) {
            return mediaType.substring(mediaType.lastIndexOf("+") + 1);
        }
        return null;
    }

    @Override
    public int compareTo(Supplement obj) {
        return this.getId().intValue() - ((Supplement) obj).getId().intValue();
    }

    @Override
    public boolean hasSupplements() {
        return false;
    }
}
