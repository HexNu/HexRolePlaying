package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.dto.in.CreateEpisodeDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class EpisodeDeserializer extends AbstractEntityDeserializer<AppEpisode, CreateEpisodeDTO>{

    public EpisodeDeserializer(CreateEpisodeDTO dto) {
        super(dto, new AppEpisode());
    }

    @Override
    public AppEpisode createEntity() {
        createSupplements(getDto().supplements);
        getEntity().setIndex(getDto().index);
        getEntity().setContent(getDto().content);
        return getEntity();
    }
    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            AppEpisodeSupplement supplement = new EpisodeSupplementDeserializer(dto).createSupplement();
            supplement.setEpisode(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

}
