package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.SupplementDTO;

/**
 *
 * @author hln
 */
public class StorySupplementAssembler extends AbstractSupplementAssembler<AppStorySupplement> {

    public StorySupplementAssembler(SupplementDTO dto) {
        super(dto, new AppStorySupplement());
    }

}
