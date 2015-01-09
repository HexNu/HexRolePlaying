package hex.rpg.core.domain;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaignSupplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import hex.rpg.core.domain.story.impl.RpgEpisode;
import hex.rpg.core.domain.story.impl.RpgEpisodeSupplement;
import hex.rpg.core.domain.story.impl.RpgStory;
import hex.rpg.core.domain.story.impl.RpgStorySupplement;

/**
 *
 * @author hln
 */
public class EntityFactory {

    public static Campaign createCampaign() {
        return createCampaign(null);
    }

    public static Campaign createCampaign(String title) {
        Campaign result = new RpgCampaign();
        result.setTitle(title);
        return result;
    }

    public static CampaignSupplement createCampaignSupplement() {
        return createCampaignSupplement(null);
    }

    public static CampaignSupplement createCampaignSupplement(String title) {
        CampaignSupplement result = new RpgCampaignSupplement();
        result.setTitle(title);
        result.setType(Supplement.DEFAULT_CAMPAIGN_TYPE);
        return result;
    }

    public static Story createStory() {
        return createStory(null);
    }

    public static Story createStory(String title) {
        Story result = new RpgStory();
        result.setTitle(title);
        return result;
    }

    public static StorySupplement createStorySupplement() {
        return createStorySupplement(null);
    }

    public static StorySupplement createStorySupplement(String title) {
        StorySupplement result = new RpgStorySupplement();
        result.setTitle(title);
        result.setType(Supplement.DEFAULT_STORY_TYPE);
        return result;
    }

    public static Episode createEpisode() {
        return createEpisode(null);
    }

    public static Episode createEpisode(String title) {
        Episode result = new RpgEpisode();
        result.setTitle(title);
        return result;
    }

    public static EpisodeSupplement createEpisodeSupplement() {
        return createEpisodeSupplement(null);
    }

    public static EpisodeSupplement createEpisodeSupplement(String title) {
        EpisodeSupplement result = new RpgEpisodeSupplement();
        result.setTitle(title);
        result.setType(Supplement.DEFAULT_EPISODE_TYPE);
        return result;
    }
}
