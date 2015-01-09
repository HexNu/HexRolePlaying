package hex.rpg.api.dto.out;

import hex.rpg.api.dto.AbstractDTO;
import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.story.Episode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class EpisodeDTO extends AbstractDTO {
    private final Long id;
    private final String title;
    private final String shortDescription;
    private final String description;
    private final String content;
    private final String refereeInfo;
    private final String refereeNotes;
    private final List<SupplementListItemDTO> supplements = new ArrayList<>();

    public EpisodeDTO(Episode episode, LinkDTOBuilder linkBuilder) {
        id = episode.getId();
        title = episode.getTitle();
        shortDescription = episode.getShortDescription();
        description = episode.getDescription();
        content = episode.getContent();
        refereeInfo = episode.getRefereeInfo();
        refereeNotes = episode.getRefereeNotes();
        episode.getSupplements().stream().forEach((supplement) -> {
            supplements.add(new SupplementListItemDTO(supplement, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getRefereeInfo() {
        return refereeInfo;
    }

    public String getRefereeNotes() {
        return refereeNotes;
    }

    public List<SupplementListItemDTO> getSupplements() {
        return supplements;
    }

}
