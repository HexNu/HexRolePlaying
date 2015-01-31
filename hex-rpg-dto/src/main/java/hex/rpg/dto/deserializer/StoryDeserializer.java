package hex.rpg.dto.deserializer;

import hex.rpg.dto.in.CreateEpisodeDTO;
import hex.rpg.dto.in.CreateStoryDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.story.impl.RpgEpisode;
import hex.rpg.jpa.domain.story.impl.RpgStory;
import hex.rpg.jpa.domain.story.impl.RpgStorySupplement;
import java.util.List;

/**
 *
 * @author hln
 */
public class StoryDeserializer extends AbstractEntityDeserializer<RpgStory, CreateStoryDTO> {

    public StoryDeserializer(CreateStoryDTO dto) {
        super(dto, new RpgStory());
    }

    @Override
    public RpgStory createEntity() {
        getEntity().setIndex(getDto().index);
        createSupplements(getDto().supplements);
        createEpisodes(getDto().episodes);
        return getEntity();
    }

    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            RpgStorySupplement supplement = new StorySupplementDeserializer(dto).createSupplement();
            supplement.setStory(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createEpisodes(List<CreateEpisodeDTO> episodeDTOs) {
        for (CreateEpisodeDTO dto : episodeDTOs) {
            RpgEpisode episode = new EpisodeDeserializer(dto).createEntity();
            episode.setStory(getEntity());
            getEntity().addEpisode(episode);
        }
    }
}
