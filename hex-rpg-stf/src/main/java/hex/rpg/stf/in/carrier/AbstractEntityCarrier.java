package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.DomainEntity;

/**
 *
 * @author hln
 */
public abstract class AbstractEntityCarrier<T extends DomainEntity> implements EntityCarrier<T> {

    private final Long tempId;
    private final Long tempParentId;
    private final T entity;

    public AbstractEntityCarrier(Long tempId, Long tempParentId, T entity) {
        this.tempId = tempId;
        this.tempParentId = tempParentId;
        this.entity = entity;
    }

    @Override
    public Long getTempId() {
        return tempId;
    }

    @Override
    public Long getTempParentId() {
        return tempParentId;
    }

    @Override
    public T getEntity() {
        return entity;
    }
}
