package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.Supplement;

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

    public FullSupplementDTO(Supplement supplement, LinkDTOBuilder linkBuilder) {
        addLink(linkBuilder.createSupplementDownloadLink(supplement));
        id = supplement.getId();
        title = supplement.getTitle();
        index = supplement.getIndex();
        shortDescription = supplement.getShortDescription();
        description = supplement.getDescription();
        refereeInfo = supplement.getRefereeInfo();
        refereeNotes = supplement.getRefereeNotes();
        type = supplement.getType();
        mediaType = supplement.getMediaType();
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

}
