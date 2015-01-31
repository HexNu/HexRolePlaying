package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.CreateSupplementDTO;

/**
 *
 * @author hln
 */
public class EpisodeSupplementDeserializer extends AbstractSupplementDeserializer<AppEpisodeSupplement>{

    public EpisodeSupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new AppEpisodeSupplement());
    }

}
