package hex.rpg.dto.deserializer;

import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.story.impl.RpgEpisodeSupplement;

/**
 *
 * @author hln
 */
public class EpisodeSupplementDeserializer extends AbstractSupplementDeserializer<RpgEpisodeSupplement>{

    public EpisodeSupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new RpgEpisodeSupplement());
    }

}
