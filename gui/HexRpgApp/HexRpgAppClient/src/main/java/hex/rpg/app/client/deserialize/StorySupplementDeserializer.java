package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.CreateSupplementDTO;

/**
 *
 * @author hln
 */
public class StorySupplementDeserializer extends AbstractSupplementDeserializer<AppStorySupplement> {

    public StorySupplementDeserializer(CreateSupplementDTO dto) {
        super(dto, new AppStorySupplement());
    }

}
