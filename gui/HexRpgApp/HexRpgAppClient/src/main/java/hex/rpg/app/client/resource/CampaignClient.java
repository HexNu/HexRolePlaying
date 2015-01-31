package hex.rpg.app.client.resource;

import com.sun.jersey.api.client.GenericType;
import hex.rpg.app.client.deserialize.CampaignDeserializer;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.dto.in.CreateCampaignDTO;
import hex.rpg.dto.out.FullCampaignDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hln
 */
public class CampaignClient extends AbstractClient {

    public List<Campaign> getAllCampaigns() {
        List<Campaign> result = new ArrayList<>();
        List<CreateCampaignDTO> dtos = getWebResource().path("campaign").get(new GenericType<List<CreateCampaignDTO>>() {
        });
        for (CreateCampaignDTO dto : dtos) {
            result.add(new CampaignDeserializer(dto).createEntity());
        }
        return result;
    }
    
    public void saveCampaign(Campaign campaign) {
        FullCampaignDTO fullCampaignDTO = new FullCampaignDTO(campaign, null);
        getWebResource().path("campaign/").accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).post(fullCampaignDTO);
    }

    public Campaign getCampaign(Long id) {
        CreateCampaignDTO result = getWebResource().path("campaign/" + id + "/full").get(new GenericType<CreateCampaignDTO>() {
        });
        return new CampaignDeserializer(result).createEntity();
    }

    public static void main(String[] args) {
        CampaignClient campaignClient = new CampaignClient();
        List<Campaign> allCampaigns = campaignClient.getAllCampaigns();
        for (Campaign campaign : allCampaigns) {
            System.out.println("Campaign: " + campaign.getTitle());
            for (Supplement supplement : campaign.getSupplements()) {
                System.out.println("CampaignSupplement: " + supplement.getTitle());
            }
            for (Story story : campaign.getStories()) {
                System.out.println("Story: " + story.getTitle());
                for (Supplement supplement : story.getSupplements()) {
                    System.out.println("StorySupplement: " + supplement.getTitle());
                }
                for (Episode episode : story.getEpisodes()) {
                    System.out.println("Episode: " + episode.getTitle());
                    for (Supplement supplement : episode.getSupplements()) {
                        System.out.println("EpisodeSupplement: " + supplement.getTitle());
                    }
                }
            }
        }
    }
}
