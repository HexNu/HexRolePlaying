package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.dto.in.CreateEpisodeDTO;
import hex.rpg.dto.in.CreateStoryDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class StoryDeserializer extends AbstractEntityDeserializer<AppStory, CreateStoryDTO> {

    public StoryDeserializer(CreateStoryDTO dto) {
        super(dto, new AppStory());
    }

    @Override
    public AppStory createEntity() {
        getEntity().setIndex(getDto().index);
        createSupplements(getDto().supplements);
        createEpisodes(getDto().episodes);
        return getEntity();
    }

    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            AppStorySupplement supplement = new StorySupplementDeserializer(dto).createSupplement();
            supplement.setStory(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createEpisodes(List<CreateEpisodeDTO> episodeDTOs) {
        for (CreateEpisodeDTO dto : episodeDTOs) {
            AppEpisode episode = new EpisodeDeserializer(dto).createEntity();
            episode.setStory(getEntity());
            getEntity().addEpisode(episode);
        }
    }
}
