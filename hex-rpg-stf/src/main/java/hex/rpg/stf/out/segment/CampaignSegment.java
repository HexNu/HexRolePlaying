package hex.rpg.stf.out.segment;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Story;
import hex.rpg.stf.out.AbstractSegment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignSegment extends AbstractSegment<Campaign> {


    public CampaignSegment(Campaign entity, DocumentBuilder builder) {
        super(entity, builder);
    }

    @Override
    public void build() {
        closeSegment();
        createSupplementSections(entity);
        List<Story> stories = new ArrayList<>(entity.getStories());
        Collections.sort(stories);
        for (Story story : stories) {
            new StorySegment(story, builder).build();
        }
        for (NonPlayingCharacter character : entity.getCharacters()) {
            new NonPlayingCharacterSegment(character, builder).build();
        }
    }
}
