package hex.rpg.app.client.resource;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.dto.in.CampaignDTO;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hln
 */
public class CampaignClient {

    public Campaign getCampaign(Long id) {
        Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8084/rpg/resources/campaign/" + id + "/full");
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (!ClientResponse.Status.OK.equals(ClientResponse.Status.fromStatusCode(response.getStatus()))) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        } else {
            CampaignDTO campaignDTO = response.getEntity(CampaignDTO.class);
            Campaign result = createFromDto(campaignDTO);
            return result;
        }
    }

    private Campaign createFromDto(CampaignDTO campaignDTO) {
        AppCampaign result = new AppCampaign();
        result.setId(campaignDTO.id);
        result.setTitle(campaignDTO.title);
        result.setShortDescription(campaignDTO.shortDescription);
        result.setDescription(campaignDTO.description);
        Campaign.Type type = Campaign.Type.getByString(campaignDTO.type);
        result.setType(type);
        result.setRefereeInfo(campaignDTO.refereeInfo);
        result.setRefereeNotes(campaignDTO.refereeNotes);
        result.setHasSupplements(campaignDTO.hasSupplements);
        result.setHasStories(campaignDTO.hasStories);
        result.setHasCharacters(campaignDTO.hasCharacters);
        return result;
    }
}
