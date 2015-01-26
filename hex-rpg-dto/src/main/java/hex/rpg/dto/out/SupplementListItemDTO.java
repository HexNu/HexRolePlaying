package hex.rpg.dto.out;

import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;

/**
 *
 * @author hln
 */
public class SupplementListItemDTO extends AbstractDTO {

    private final String title;
    private final String shortDescription;
    private final String mediaType;

    public SupplementListItemDTO(Supplement supplement, LinkDTOBuilder linkBuilder) {
        addLink(linkBuilder.createSupplementDownloadLink(supplement));
        title = supplement.getTitle();
        shortDescription = supplement.getShortDescription();
        mediaType = supplement.getMediaType();
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getMediaType() {
        return mediaType;
    }
}
