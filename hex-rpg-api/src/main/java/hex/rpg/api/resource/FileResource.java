package hex.rpg.api.resource;

import hex.rpg.api.dto.LinkDTOBuilder;
import hex.rpg.api.dto.out.CampaignDTO;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.service.command.campaign.FindAllCampaignsCommand;
import hex.rpg.service.command.campaign.GetCampaignCommand;
import hex.rpg.service.command.cff.CreateXmlCampaignFile;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
@Path("cff")
public class FileResource extends AbstractResource {

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getCampaign(@QueryParam("id") Long id) {
        List<Campaign> resultList = new ArrayList<>();
        if (id == null) {
            resultList.addAll(commandExecutor.execute(new FindAllCampaignsCommand(), getKey()));
        } else {
            resultList.add(commandExecutor.execute(new GetCampaignCommand(id), getKey()));
        }
        if (resultList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        XmlNode result = commandExecutor.execute(new CreateXmlCampaignFile(resultList), getKey());
        return Response.ok(result.toString()).build();
    }

}
