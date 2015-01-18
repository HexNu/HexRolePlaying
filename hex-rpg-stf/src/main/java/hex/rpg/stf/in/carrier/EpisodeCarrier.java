package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.story.Episode;

/**
 *
 * @author hln
 */
public class EpisodeCarrier extends AbstractEntityCarrier<Episode> {

    public EpisodeCarrier(Long tempId, Long tempParentId, Episode entity) {
        super(tempId, tempParentId, entity);
    }

}
