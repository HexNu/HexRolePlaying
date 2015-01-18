package hex.rpg.stf;

import hex.rpg.core.domain.DomainEntity;

/**
 *
 * @author hln
 * @param <T>
 */
public interface Segment<T extends DomainEntity> {

    public static final String SEGMENT_DELIMITER = "^;$",
            REGEX_NEW_FIELD = Field.Label.regEx(),
            REGEX_END_OF_SEGMENT = "^" + SEGMENT_DELIMITER + "$";
}
