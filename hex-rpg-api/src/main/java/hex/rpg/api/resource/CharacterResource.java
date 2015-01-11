package hex.rpg.api.resource;

import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.service.command.character.FindAllNonPlayingCharactersCommand;
import java.util.List;
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
@Path("character")
public class CharacterResource extends AbstractResource {

    @GET
    @Path("{campaignId}/npc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNpcs(@PathParam("campaignId") Long campaignId) {
        List<NonPlayingCharacter> resultList = commandExecutor.execute(new FindAllNonPlayingCharactersCommand(), getKey());
        
        return Response.ok().build();
    }
    
}
