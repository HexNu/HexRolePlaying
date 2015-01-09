package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.Supplement;

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
