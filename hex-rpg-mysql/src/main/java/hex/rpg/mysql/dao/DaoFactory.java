package hex.rpg.mysql.dao;

import javax.persistence.EntityManager;

/**
 * Created 2014-12-10
 *
 * @author hl
 */
public class DaoFactory {

    private final EntityManager em;

    public DaoFactory(EntityManager em) {
        this.em = em;
    }

    public CampaignDao getCampaignDao() {
        return new CampaignDao(em);
    }
    
    public CampaignSupplementDao getCampaignSupplementDao() {
        return new CampaignSupplementDao(em);
    }

    public StoryDao getStoryDao() {
        return new StoryDao(em);
    }
    
    public StorySupplementDao getStorySupplementDao() {
        return new StorySupplementDao(em);
    }
    
    public EpisodeDao getEpisodeDao() {
        return new EpisodeDao(em);
    }
    
    public EpisodeSupplementDao getEpisodeSupplementDao() {
        return new EpisodeSupplementDao(em);
    }
    
    public PlayingCharacterDao getPlayingCharacterDao() {
        return new PlayingCharacterDao(em);
    }
    
    public PlayingCharacterSupplementDao getPlayingCharacterSupplementDao() {
        return new PlayingCharacterSupplementDao(em);
    }
    
    public NonPlayingCharacterDao getNonPlayingCharacterDao() {
        return new NonPlayingCharacterDao(em);
    }
    public NonPlayingCharacterSupplementDao getNonPlayingCharacterSupplementDao() {
        return new NonPlayingCharacterSupplementDao(em);
    }
}
