package hex.rpg.app.campaign.node;

import hex.rpg.app.domain.character.AppNonPlayingCharacter;
import hex.rpg.core.domain.CharacterEntity;

/**
 *
 * @author hln
 */
public class CharacterNode extends AbstractRpgNode<AppNonPlayingCharacter> {

    public CharacterNode(AppNonPlayingCharacter entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        CharacterEntity.Gender gender = getEntity().getGender();
        if (gender.equals(CharacterEntity.Gender.FEMALE)) {
            setIcon("woman");
        } else if (gender.equals(CharacterEntity.Gender.OTHER)) {
            setIcon("alien");
        } else if (gender.equals(CharacterEntity.Gender.NA)) {
            setIcon("easter-egg-spiral");
        }
    }

}
