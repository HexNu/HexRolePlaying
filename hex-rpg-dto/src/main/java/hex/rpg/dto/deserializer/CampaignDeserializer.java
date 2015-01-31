package hex.rpg.dto.deserializer;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.dto.in.CreateCampaignDTO;
import hex.rpg.dto.in.CreateStoryDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaign;
import hex.rpg.jpa.domain.campaign.impl.RpgCampaignSupplement;
import hex.rpg.jpa.domain.story.impl.RpgStory;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignDeserializer extends AbstractEntityDeserializer<RpgCampaign, CreateCampaignDTO> {

    public CampaignDeserializer(CreateCampaignDTO dto) {
        super(dto, new RpgCampaign());
    }

    @Override
    public RpgCampaign createEntity() {
        getEntity().setType(Campaign.Type.getByString(getDto().type));
        createSupplements(getDto().supplements);
        createStories(getDto().stories);
        return getEntity();
    }
    
    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            RpgCampaignSupplement supplement = new CampaignSupplementDeserializer(dto).createSupplement();
            supplement.setCampaign(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createStories(List<CreateStoryDTO> storieDTOs) {
        for (CreateStoryDTO dto : storieDTOs) {
            RpgStory story = new StoryDeserializer(dto).createEntity();
            story.setCampaign(getEntity());
            getEntity().addStory(story);
        }
    }
}
