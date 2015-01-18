package hex.rpg.stf.in;

import hex.rpg.core.domain.DomainEntity;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hln
 */
public class CampaignCarrier {

    Map<Long, EntityCarrier> entityCarriers = new HashMap<>();

    public void addEntity(Long tempId, Long tempParentId, DomainEntity entity) {
        entityCarriers.put(tempId, new EntityCarrier(tempParentId, entity));
    }

    public Map<Long, EntityCarrier> getAllEntityCarriers() {
        return entityCarriers;
    }

    public class EntityCarrier {

        private final Long tempParentId;
        private final DomainEntity entity;

        public EntityCarrier(Long tempParentId, DomainEntity entity) {
            this.tempParentId = tempParentId;
            this.entity = entity;
        }

        public Long getTempParentId() {
            return tempParentId;
        }

        public DomainEntity getEntity() {
            return entity;
        }
    }
}
