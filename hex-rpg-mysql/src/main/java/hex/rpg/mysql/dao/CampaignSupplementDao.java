package hex.rpg.mysql.dao;

import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaignSupplement;
import javax.persistence.EntityManager;

/**
 *
 * @author hln
 */
public class CampaignSupplementDao extends GenericDao<CampaignSupplement, Long> {

    public CampaignSupplementDao(EntityManager entityManager) {
        super(RpgCampaignSupplement.class, entityManager);
    }

}
