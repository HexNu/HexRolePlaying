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

    public StoryDao getStoryDao() {
        return new StoryDao(em);
    }
    
    public EpisodeDao getEpisodeDao() {
        return new EpisodeDao(em);
    }
    
    public PlayingCharacterDao getPlayingCharacterDao() {
        return new PlayingCharacterDao(em);
    }
    
    public NonPlayingCharacterDao getNonPlayingCharacterDao() {
        return new NonPlayingCharacterDao(em);
    }
}
