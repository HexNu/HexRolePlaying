package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.SupplementDTO;

/**
 *
 * @author hln
 */
public class EpisodeSupplementAssembler extends AbstractSupplementAssembler<AppEpisodeSupplement>{

    public EpisodeSupplementAssembler(SupplementDTO dto) {
        super(dto, new AppEpisodeSupplement());
    }

}
