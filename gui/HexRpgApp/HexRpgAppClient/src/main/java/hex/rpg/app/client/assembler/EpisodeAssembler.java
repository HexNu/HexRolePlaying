package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.dto.in.EpisodeDTO;
import hex.rpg.dto.in.SupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class EpisodeAssembler extends AbstractEntityAssembler<AppEpisode, EpisodeDTO>{

    public EpisodeAssembler(EpisodeDTO dto) {
        super(dto, new AppEpisode());
    }

    @Override
    public AppEpisode createEntity() {
        createSupplements(getDto().supplements);
        getEntity().setIndex(getDto().index);
        getEntity().setContent(getDto().content);
        return getEntity();
    }
    private void createSupplements(List<SupplementDTO> supplementDTOs) {
        for (SupplementDTO dto : supplementDTOs) {
            AppEpisodeSupplement supplement = new EpisodeSupplementAssembler(dto).createSupplement();
            supplement.setEpisode(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

}
