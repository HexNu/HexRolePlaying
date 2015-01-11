package hex.rpg.api.resource;

import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.api.dto.out.CampaignDTO;
import hex.rpg.api.dto.out.EpisodeDTO;
import hex.rpg.api.dto.out.StoryDTO;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.service.command.campaign.GetCampaignCommand;
import hex.rpg.service.command.campaign.GetEpisodeCommand;
import hex.rpg.service.command.story.GetStoryCommand;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author hln
 */
@Path("campaign")
public class CampaignResource extends AbstractResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCampaign(@PathParam("id") Long id) {
        Campaign campaign = commandExecutor.execute(new GetCampaignCommand(id), getKey());
        if (campaign == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LinkDTOBuilder linkDTOBuilder = new LinkDTOBuilder(getBaseUri());
        CampaignDTO result = new CampaignDTO(campaign, linkDTOBuilder);
        return Response.ok(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{campaignId}/{id}")
    public Response getStory(@PathParam("id") Long id) {
        Story story = commandExecutor.execute(new GetStoryCommand(id), getKey());
        if (story == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LinkDTOBuilder linkDTOBuilder = new LinkDTOBuilder(getBaseUri());
        StoryDTO result = new StoryDTO(story, linkDTOBuilder);
        return Response.ok(result).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{campaignId}/{storyId}/{id}")
    public Response getEpisode(@PathParam("id") Long id) {
        Episode episode = commandExecutor.execute(new GetEpisodeCommand(id), getKey());
        if (episode == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LinkDTOBuilder linkDTOBuilder = new LinkDTOBuilder(getBaseUri());
        EpisodeDTO result = new EpisodeDTO(episode, linkDTOBuilder);
        return Response.ok(result).build();
    }
}
