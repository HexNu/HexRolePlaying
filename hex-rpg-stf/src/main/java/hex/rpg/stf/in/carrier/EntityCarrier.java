package hex.rpg.stf.in.carrier;

import hex.rpg.core.domain.DomainEntity;

/**
 *
 * @author hln
 * @param <T>
 */
public interface EntityCarrier<T extends DomainEntity> {

    T getEntity();

    Long getTempId();

    Long getTempParentId();
}
