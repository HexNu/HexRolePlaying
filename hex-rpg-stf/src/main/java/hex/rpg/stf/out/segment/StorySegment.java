package hex.rpg.stf.out.segment;

import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.stf.Field;
import hex.rpg.stf.out.AbstractSegment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 */
public class StorySegment extends AbstractSegment<Story> {

    public StorySegment(Story entity, DocumentBuilder builder) {
        super(entity, builder);
    }

    @Override
    public void build() {
        builder.append(Field.Label.X).append(entity.getIndex());
        closeSegment();
        createSupplementSections(entity);
        List<Episode> episodes = new ArrayList<>(entity.getEpisodes());
        Collections.sort(episodes);
        for (Episode episode : episodes) {
            new EpisodeSegment(episode, builder).build();
        }
    }
}
