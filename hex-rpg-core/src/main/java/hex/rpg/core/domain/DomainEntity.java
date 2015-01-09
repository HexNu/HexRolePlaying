package hex.rpg.core.domain;

import java.io.Serializable;

/**
 *
 * @author hln
 */
public interface DomainEntity extends Serializable {

    Long getId();

    String getName();
}
