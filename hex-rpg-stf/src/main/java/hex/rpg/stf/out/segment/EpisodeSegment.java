package hex.rpg.stf.out.segment;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.stf.Field;
import hex.rpg.stf.out.AbstractSegment;

/**
 *
 * @author hln
 */
public class EpisodeSegment extends AbstractSegment<Episode> {

    public EpisodeSegment(Episode entity, DocumentBuilder builder) {
        super(entity, builder);
    }

    @Override
    public void build() {
        builder.append(Field.Label.X).append(entity.getIndex());
        builder.append(Field.Label.C).append(entity.getContent());
        closeSegment();
        createSupplementSections(entity);
    }

}
