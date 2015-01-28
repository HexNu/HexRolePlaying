package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.dto.in.EpisodeDTO;
import hex.rpg.dto.in.StoryDTO;
import hex.rpg.dto.in.SupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class StoryAssembler extends AbstractEntityAssembler<AppStory, StoryDTO> {

    public StoryAssembler(StoryDTO dto) {
        super(dto, new AppStory());
    }

    @Override
    public AppStory createEntity() {
        getEntity().setIndex(getDto().index);
        createSupplements(getDto().supplements);
        createEpisodes(getDto().episodes);
        return getEntity();
    }

    private void createSupplements(List<SupplementDTO> supplementDTOs) {
        for (SupplementDTO dto : supplementDTOs) {
            AppStorySupplement supplement = new StorySupplementAssembler(dto).createSupplement();
            supplement.setStory(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createEpisodes(List<EpisodeDTO> episodeDTOs) {
        for (EpisodeDTO dto : episodeDTOs) {
            AppEpisode episode = new EpisodeAssembler(dto).createEntity();
            episode.setStory(getEntity());
            getEntity().addEpisode(episode);
        }
    }
}
