package hex.rpg.api.resource;

import hex.rpg.core.HexMediaType;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.service.command.campaign.CreateZippedTexCampaignFileCommand;
import hex.rpg.service.command.campaign.FindAllCampaignsCommand;
import hex.rpg.service.command.campaign.GetCampaignCommand;
import hex.rpg.service.command.cfx.CreateCfxDoc;
import hex.rpg.service.command.cfx.CreateCfxInputStream;
import hex.rpg.service.command.character.FindAllPlayingCharactersCommand;
import hex.rpg.service.command.stf.GetSimpleTextFormatFileCommand;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author hln
 */
@Path("cfx")
public class FileResource extends AbstractResource {

    @GET
    @Produces(HexMediaType.APPLICATION_CFX)
    public Response getCampaign(@QueryParam("id") Long id) {
        List<Campaign> campaigns = new ArrayList<>();
        if (id == null) {
            campaigns.addAll(commandExecutor.execute(new FindAllCampaignsCommand(), getKey()));
        } else {
            campaigns.add(commandExecutor.execute(new GetCampaignCommand(id), getKey()));
        }
        if (campaigns.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<PlayingCharacter> playingCharacters = commandExecutor.execute(new FindAllPlayingCharactersCommand(), getKey());
        InputStream result = commandExecutor.execute(new CreateCfxInputStream(campaigns.get(0), playingCharacters), getKey());
        return Response.ok((Object) result).type(HexMediaType.APPLICATION_CFX)
                .header("Content-Disposition", "attachment; filename=\"campaigns.cfx\"").build();
    }

    @GET
    @Path("xml")
    @Produces(HexMediaType.APPLICATION_CFXF)
    public Response getCampaignXml(@QueryParam("id") Long id) {
        List<Campaign> resultList = new ArrayList<>();
        if (id == null) {
            resultList.addAll(commandExecutor.execute(new FindAllCampaignsCommand(), getKey()));
        } else {
            resultList.add(commandExecutor.execute(new GetCampaignCommand(id), getKey()));
        }
        if (resultList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<PlayingCharacter> playingCharacters = commandExecutor.execute(new FindAllPlayingCharactersCommand(), getKey());
        String result = commandExecutor.execute(new CreateCfxDoc(resultList, playingCharacters), getKey()).toString();
        return Response.ok(result).type(HexMediaType.APPLICATION_CFXF)
                .header("Content-Disposition", "attachment; filename=\"campaigns.cfxf\"").build();
    }

    @GET
    @Path("tex/{id}")
    @Produces(HexMediaType.APPLICATION_CFT)
    public Response getCampaignLaTeX(@PathParam("id") Long id) {
        Campaign campaign = commandExecutor.execute(new GetCampaignCommand(id), getKey());
        if (campaign == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        InputStream result = commandExecutor.execute(new CreateZippedTexCampaignFileCommand(campaign), getKey());
        return Response.ok((Object) result).type(HexMediaType.APPLICATION_CFT)
                .header("Content-Disposition", "attachment; filename=\"" + campaign.getTitle().replaceAll(" ", "_") + ".cft\"").build();
    }

    @GET
    @Path("stf/{id}")
    @Produces(HexMediaType.APPLICATION_STF)
    public Response getSimpleCampaignTextFile(@PathParam("id") Long id) {
        Campaign campaign = commandExecutor.execute(new GetCampaignCommand(id), getKey());
        if (campaign == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        InputStream result = commandExecutor.execute(new GetSimpleTextFormatFileCommand(campaign), getKey());
        return Response.ok((Object) result).type(HexMediaType.APPLICATION_CFT)
                .header("Content-Disposition", "attachment; filename=\"" + campaign.getTitle().replaceAll(" ", "_") + ".stf\"").build();
    }
}
