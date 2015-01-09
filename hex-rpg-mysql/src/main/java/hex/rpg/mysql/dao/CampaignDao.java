package hex.rpg.mysql.dao;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class CampaignDao extends GenericDao<Campaign, Long> {

    public CampaignDao(EntityManager entityManager) {
        super(RpgCampaign.class, entityManager);
    }

    @Override
    public Campaign save(Campaign campaign) {
        if (campaign.getId() == null) {
            return super.save(campaign);
        } else {
            return super.update(campaign);
        }
    }
}
