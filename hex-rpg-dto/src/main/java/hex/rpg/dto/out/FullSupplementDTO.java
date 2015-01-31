package hex.rpg.dto.out;

import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;

/**
 *
 * @author hln
 */
public class FullSupplementDTO extends AbstractDTO {

    private final Long id;
    private final String title;
    private final Integer index;
    private final String shortDescription;
    private final String description;
    private final String refereeInfo;
    private final String refereeNotes;
    private final Supplement.Type type;
    private final String mediaType;
//    private final byte[] content;

    public FullSupplementDTO(Supplement supplement, LinkDTOBuilder linkBuilder) {
        if (linkBuilder != null) {
            addLink(linkBuilder.createSupplementDownloadLink(supplement));
        }
        id = supplement.getId();
        title = supplement.getTitle();
        index = supplement.getIndex();
        shortDescription = supplement.getShortDescription();
        description = supplement.getDescription();
        refereeInfo = supplement.getRefereeInfo();
        refereeNotes = supplement.getRefereeNotes();
        type = supplement.getType();
        mediaType = supplement.getMediaType();
//        content = supplement.getContentAsByteArray();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getIndex() {
        return index;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getRefereeInfo() {
        return refereeInfo;
    }

    public String getRefereeNotes() {
        return refereeNotes;
    }

    public Supplement.Type getType() {
        return type;
    }

    public String getMediaType() {
        return mediaType;
    }
//
//    public byte[] getContent() {
//        return content;
//    }
}
