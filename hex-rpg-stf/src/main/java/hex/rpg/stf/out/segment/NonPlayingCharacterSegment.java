package hex.rpg.stf.out.segment;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.stf.Field;
import hex.rpg.stf.out.AbstractSegment;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterSegment extends AbstractSegment<NonPlayingCharacter> {

    public NonPlayingCharacterSegment(NonPlayingCharacter entity, DocumentBuilder builder) {
        super(entity, builder);
    }

    @Override
    public void build() {
        builder.append(Field.Label.I).append(entity.getRefereeInfo());
        builder.append(Field.Label.N).append(entity.getRefereeNotes());
        closeSegment();
        createSupplementSections(entity);
    }
}
