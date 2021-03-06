package hex.rpg.core.domain;

import java.io.Serializable;

/**
 *
 * @author hln
 */
public interface DomainEntity extends Serializable {

    Long getId();
    
    Long getParentId();

    String getName();
    
    String getShortDescription();
}
