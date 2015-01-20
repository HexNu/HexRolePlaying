package hex.rpg.api.resource;

import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.service.command.character.FindAllNonPlayingCharactersCommand;
import hex.rpg.service.command.character.GetNonPlayingCharacterCommand;
import hex.rpg.service.command.character.GetNonPlayingCharacterSupplementCommand;
import java.io.InputStream;
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
@Path("npc")
public class CharacterResource extends AbstractResource {

    @GET
    @Path("{campaignId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNpcs(@PathParam("campaignId") Long campaignId) {
        List<NonPlayingCharacter> resultList = commandExecutor.execute(new FindAllNonPlayingCharactersCommand(), getKey());

        return Response.ok().build();
    }

    @GET
    @Path("{id}/portrait")
    public Response getPortrait(@PathParam("id") Long id) {
        NonPlayingCharacter character = commandExecutor.execute(new GetNonPlayingCharacterCommand(id), getKey());
        InputStream portrait = character.getPortrait();
        String mediaType = character.getPortraitMediaType();
        String filename = character.getName().replaceAll(" ", "_") + getSuffixFromMediaType(mediaType);
        return Response.ok((Object) portrait).type(mediaType)
                .header("Content-Disposition", getAttachmentStringFromFilename(filename)).build();
    }
}
