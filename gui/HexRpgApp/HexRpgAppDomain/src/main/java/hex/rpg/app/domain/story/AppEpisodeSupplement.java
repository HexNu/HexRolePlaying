package hex.rpg.app.domain.story;

import hex.rpg.app.domain.AbstractSupplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;

/**
 *
 * @author hln
 */
public class AppEpisodeSupplement extends AbstractSupplement implements EpisodeSupplement {
    private Episode episode;

    @Override
    protected String getOwnerClassName() {
        return "Episode";
    }

    @Override
    public Long getParentId() {
        return episode != null ? episode.getId() : null;
    }

    @Override
    public Episode getEpisode() {
        return episode;
    }

    @Override
    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
