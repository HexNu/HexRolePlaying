package hex.rpg.core.domain.story;

import hex.rpg.core.domain.Supplement;

/**
 *
 * @author hln
 */
public interface EpisodeSupplement extends Supplement {

    Episode getEpisode();

    void setEpisode(Episode episode);
}
