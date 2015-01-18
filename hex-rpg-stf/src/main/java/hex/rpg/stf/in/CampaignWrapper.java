package hex.rpg.stf.in;

import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import hex.rpg.stf.in.carrier.CampaignCarrier;
import hex.rpg.stf.in.carrier.CampaignSupplementCarrier;
import hex.rpg.stf.in.carrier.EpisodeCarrier;
import hex.rpg.stf.in.carrier.EpisodeSupplementCarrier;
import hex.rpg.stf.in.carrier.NonPlayingCharacterCarrier;
import hex.rpg.stf.in.carrier.NonPlayingCharacterSupplementCarrier;
import hex.rpg.stf.in.carrier.StoryCarrier;
import hex.rpg.stf.in.carrier.StorySupplementCarrier;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignWrapper {

    CampaignCarrier campaignCarrier;
    List<StoryCarrier> stories = new ArrayList<>();
    List<EpisodeCarrier> episodes = new ArrayList<>();
    List<NonPlayingCharacterCarrier> nonPlayingCharacters = new ArrayList<>();
    List<CampaignSupplementCarrier> campaignSupplements = new ArrayList<>();
    List<StorySupplementCarrier> storySupplements = new ArrayList<>();
    List<EpisodeSupplementCarrier> episodeSupplements = new ArrayList<>();
    List<NonPlayingCharacterSupplementCarrier> nonPlayingCharacterSupplements = new ArrayList<>();

    public void addToWrapper(Long tempId, Long tempParentId, DomainEntity entity) {
        if (entity instanceof Campaign) {
            addCampaign(tempId, tempParentId, (Campaign) entity);
        } else if (entity instanceof Story) {
            addStory(tempId, tempParentId, (Story) entity);
        } else if (entity instanceof Episode) {
            addEpisode(tempId, tempParentId, (Episode) entity);
        } else if (entity instanceof NonPlayingCharacter) {
            addNonPlayingCharacter(tempId, tempParentId, (NonPlayingCharacter) entity);
        } else if (entity instanceof CampaignSupplement) {
            addCampaignSupplement(tempParentId, (CampaignSupplement) entity);
        } else if (entity instanceof StorySupplement) {
            addStorySupplement(tempParentId, (StorySupplement) entity);
        } else if (entity instanceof EpisodeSupplement) {
            addEpisodeSupplement(tempParentId, (EpisodeSupplement) entity);
        } else if (entity instanceof NonPlayingCharacterSupplement) {
            addNonPlayingCharacterSupplement(tempParentId, (NonPlayingCharacterSupplement) entity);
        }
    }

    public Campaign build() {
        Campaign result = campaignCarrier.getEntity();
        for (CampaignSupplementCarrier carrier : campaignSupplements) {
            result.addSupplement(carrier.getEntity());
        }
        for (NonPlayingCharacterCarrier nonPlayingCharacterCarrier : nonPlayingCharacters) {
            NonPlayingCharacter nonPlayingCharacter = nonPlayingCharacterCarrier.getEntity();
            result.addCharacter(nonPlayingCharacter);
            nonPlayingCharacter.setCampaign(result);
            for (NonPlayingCharacterSupplementCarrier nonPlayingCharacterSupplementCarrier : nonPlayingCharacterSupplements) {
                if (nonPlayingCharacterSupplementCarrier.getTempParentId().equals(nonPlayingCharacterCarrier.getTempId())) {
                    NonPlayingCharacterSupplement nonPlayingCharacterSupplement = nonPlayingCharacterSupplementCarrier.getEntity();
                    nonPlayingCharacter.addSupplement(nonPlayingCharacterSupplement);
                    nonPlayingCharacterSupplement.setNonPlayingCharacter(nonPlayingCharacter);
                }
            }
        }
        for (StoryCarrier storyCarrier : stories) {
            Story story = storyCarrier.getEntity();
            result.addStory(story);
            story.setCampaign(result);
            for (StorySupplementCarrier storySupplementCarrier : storySupplements) {
                if (storySupplementCarrier.getTempParentId().equals(storyCarrier.getTempId())) {
                    StorySupplement storySupplement = storySupplementCarrier.getEntity();
                    story.addSupplement(storySupplement);
                    storySupplement.setStory(story);
                }
            }
            for (EpisodeCarrier episodeCarrier : episodes) {
                if (episodeCarrier.getTempParentId().equals(storyCarrier.getTempId())) {
                    Episode episode = episodeCarrier.getEntity();
                    story.addEpisode(episode);
                    episode.setStory(story);
                    for (EpisodeSupplementCarrier episodeSupplementCarrier : episodeSupplements) {
                        if (episodeSupplementCarrier.getTempParentId().equals(episodeCarrier.getTempId())) {
                            EpisodeSupplement episodeSupplement = episodeSupplementCarrier.getEntity();
                            episode.addSupplement(episodeSupplement);
                            episodeSupplement.setEpisode(episode);
                        }
                    }
                }
            }
        }
        return result;
    }

    public void addCampaign(Long tempId, Long tempParentId, Campaign campaign) {
        campaignCarrier = new CampaignCarrier(tempId, tempParentId, campaign);
    }

    public void addStory(Long tempId, Long tempParentId, Story story) {
        stories.add(new StoryCarrier(tempId, tempParentId, story));
    }

    public void addEpisode(Long tempId, Long tempParentId, Episode episode) {
        episodes.add(new EpisodeCarrier(tempId, tempParentId, episode));
    }

    public void addNonPlayingCharacter(Long tempId, Long tempParentId, NonPlayingCharacter nonPlayingCharacter) {
        nonPlayingCharacters.add(new NonPlayingCharacterCarrier(tempId, tempParentId, nonPlayingCharacter));
    }

    public void addCampaignSupplement(Long tempParentId, CampaignSupplement supplement) {
        campaignSupplements.add(new CampaignSupplementCarrier(null, tempParentId, supplement));
    }

    public void addStorySupplement(Long tempParentId, StorySupplement supplement) {
        storySupplements.add(new StorySupplementCarrier(null, tempParentId, supplement));
    }

    public void addEpisodeSupplement(Long tempParentId, EpisodeSupplement supplement) {
        episodeSupplements.add(new EpisodeSupplementCarrier(null, tempParentId, supplement));
    }

    public void addNonPlayingCharacterSupplement(Long tempParentId, NonPlayingCharacterSupplement supplement) {
        nonPlayingCharacterSupplements.add(new NonPlayingCharacterSupplementCarrier(null, tempParentId, supplement));
    }
}
