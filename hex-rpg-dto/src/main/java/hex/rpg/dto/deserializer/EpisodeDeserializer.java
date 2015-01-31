package hex.rpg.dto.deserializer;

import hex.rpg.dto.in.CreateEpisodeDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.story.impl.RpgEpisode;
import hex.rpg.jpa.domain.story.impl.RpgEpisodeSupplement;
import java.util.List;

/**
 *
 * @author hln
 */
public class EpisodeDeserializer extends AbstractEntityDeserializer<RpgEpisode, CreateEpisodeDTO>{

    public EpisodeDeserializer(CreateEpisodeDTO dto) {
        super(dto, new RpgEpisode());
    }

    @Override
    public RpgEpisode createEntity() {
        createSupplements(getDto().supplements);
        getEntity().setIndex(getDto().index);
        getEntity().setContent(getDto().content);
        return getEntity();
    }
    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            RpgEpisodeSupplement supplement = new EpisodeSupplementDeserializer(dto).createSupplement();
            supplement.setEpisode(getEntity());
            getEntity().addSupplement(supplement);
        }
    }
}
