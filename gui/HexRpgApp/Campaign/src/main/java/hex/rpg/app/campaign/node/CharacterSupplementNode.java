package hex.rpg.app.campaign.node;

import hex.rpg.app.domain.character.AppNonPlayingCharacterSupplement;

/**
 *
 * @author hln
 */
public class CharacterSupplementNode extends AbstractRpgNode<AppNonPlayingCharacterSupplement> {

    public CharacterSupplementNode(AppNonPlayingCharacterSupplement entity) {
        super(entity);
    }

    @Override
    protected void setup() {
        setIcon("file-picture-text");
    }

}
