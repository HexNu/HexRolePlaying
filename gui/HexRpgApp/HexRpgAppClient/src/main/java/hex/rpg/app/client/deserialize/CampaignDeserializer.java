package hex.rpg.app.client.deserialize;

import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.dto.in.CreateCampaignDTO;
import hex.rpg.dto.in.CreateStoryDTO;
import hex.rpg.dto.in.CreateSupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignDeserializer extends AbstractEntityDeserializer<AppCampaign, CreateCampaignDTO> {

    public CampaignDeserializer(CreateCampaignDTO dto) {
        super(dto, new AppCampaign());
    }

    @Override
    public AppCampaign createEntity() {
        getEntity().setType(Campaign.Type.getByString(getDto().type));
        createSupplements(getDto().supplements);
        createStories(getDto().stories);
        return getEntity();
    }
    
    private void createSupplements(List<CreateSupplementDTO> supplementDTOs) {
        for (CreateSupplementDTO dto : supplementDTOs) {
            AppCampaignSupplement supplement = new CampaignSupplementDeserializer(dto).createSupplement();
            supplement.setCampaign(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createStories(List<CreateStoryDTO> storieDTOs) {
        for (CreateStoryDTO dto : storieDTOs) {
            AppStory story = new StoryDeserializer(dto).createEntity();
            story.setCampaign(getEntity());
            getEntity().addStory(story);
        }
    }
}
