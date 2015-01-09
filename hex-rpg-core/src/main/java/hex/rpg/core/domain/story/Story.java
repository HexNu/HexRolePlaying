package hex.rpg.core.domain.story;

import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.List;

/**
 *
 * @author hln
 */
public interface Story extends NarrativeEntity, Comparable {

    Campaign getCampaign();

    void setCampaign(Campaign campaign);

    int getIndex();

    void setIndex(int index);

    List<Episode> getEpisodes();

    void addEpisode(int index, Episode episode);

    void addEpisode(Episode episode);
}
