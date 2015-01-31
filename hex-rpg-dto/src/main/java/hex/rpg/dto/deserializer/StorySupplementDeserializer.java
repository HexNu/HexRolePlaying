package hex.rpg.dto.deserializer;

import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.story.impl.RpgStorySupplement;

/**
 *
 * @author hln
 */
public class StorySupplementDeserializer extends AbstractSupplementDeserializer<RpgStorySupplement> {

    public StorySupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new RpgStorySupplement());
    }

}
