package hex.rpg.app.domain.story;

import hex.rpg.app.domain.AbstractNarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class AppStory extends AbstractNarrativeEntity implements Story {

    private Campaign campaign;
    private Integer index;
    private final List<Episode> episodes = new ArrayList<>();
    private final List<Supplement> supplements = new ArrayList<>();

    @Override
    public List<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof StorySupplement) {
            ((StorySupplement) supplement).setStory(this);
            supplements.add(supplement);
        }
    }

    @Override
    public boolean hasSupplements() {
        return !supplements.isEmpty();
    }

    @Override
    public Long getParentId() {
        return campaign != null ? campaign.getId() : null;
    }

    @Override
    public Campaign getCampaign() {
        return campaign;
    }

    @Override
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public void addEpisode(int index, Episode episode) {
        episodes.add(index, episode);
        episode.setStory(this);
    }

    @Override
    public void addEpisode(Episode episode) {
        episodes.add(episode);
        episode.setStory(this);
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Story) {
            return this.getIndex() - ((Story) obj).getIndex();
        }
        return 0;
    }

}
