package hex.rpg.app.client.assembler;

import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.dto.in.CampaignDTO;
import hex.rpg.dto.in.StoryDTO;
import hex.rpg.dto.in.SupplementDTO;
import java.util.List;

/**
 *
 * @author hln
 */
public class CampaignAssembler extends AbstractEntityAssembler<AppCampaign, CampaignDTO> {

    public CampaignAssembler(CampaignDTO dto) {
        super(dto, new AppCampaign());
    }

    @Override
    public AppCampaign createEntity() {
        getEntity().setType(Campaign.Type.getByString(getDto().type));
        createSupplements(getDto().supplements);
        createStories(getDto().stories);
        return getEntity();
    }
    
    private void createSupplements(List<SupplementDTO> supplementDTOs) {
        for (SupplementDTO dto : supplementDTOs) {
            AppCampaignSupplement supplement = new CampaignSupplementAssembler(dto).createSupplement();
            supplement.setCampaign(getEntity());
            getEntity().addSupplement(supplement);
        }
    }

    private void createStories(List<StoryDTO> storieDTOs) {
        for (StoryDTO dto : storieDTOs) {
            AppStory story = new StoryAssembler(dto).createEntity();
            story.setCampaign(getEntity());
            getEntity().addStory(story);
        }
    }
}
