package hex.rpg.api.resource;

import static hex.rpg.api.resource.AbstractResource.getSuffixFromMediaType;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.StorySupplement;
import hex.rpg.service.command.campaign.GetCampaignSupplementCommand;
import hex.rpg.service.command.character.GetNonPlayingCharacterSupplementCommand;
import hex.rpg.service.command.episode.GetEpisodeSupplementCommand;
import hex.rpg.service.command.story.GetStorySupplementCommand;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author hln
 */
@Path("supplement")
public class SupplementResource extends AbstractResource {

    @GET
    @Path("download/campaign/{id}")
    public Response getCampaignSupplement(@PathParam("id") Long id) {
        CampaignSupplement supplement = commandExecutor.execute(new GetCampaignSupplementCommand(id), getKey());
        if (supplement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String filename = supplement.getCampaign().getTitle() + " - Supplement-"
                + supplement.getIndex() + getSuffixFromMediaType(supplement.getMediaType());
        return Response.ok((Object) supplement.getContent()).type(supplement.getMediaType())
                .header("Content-Disposition", getAttachmentStringFromFilename(filename)).build();
    }

    @GET
    @Path("download/story/{id}")
    public Response getStorySupplement(@PathParam("id") Long id) {
        StorySupplement supplement = commandExecutor.execute(new GetStorySupplementCommand(id), getKey());
        if (supplement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String filename = supplement.getStory().getTitle() + " - Supplement-"
                + supplement.getIndex() + getSuffixFromMediaType(supplement.getMediaType());
        return Response.ok((Object) supplement.getContent()).type(supplement.getMediaType())
                .header("Content-Disposition", getAttachmentStringFromFilename(filename)).build();
    }

    @GET
    @Path("download/episode/{id}")
    public Response getEpisodeSupplement(@PathParam("id") Long id) {
        EpisodeSupplement supplement = commandExecutor.execute(new GetEpisodeSupplementCommand(id), getKey());
        if (supplement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String filename = supplement.getEpisode().getTitle() + " - Supplement-"
                + supplement.getIndex() + getSuffixFromMediaType(supplement.getMediaType());
        return Response.ok((Object) supplement.getContent()).type(supplement.getMediaType())
                .header("Content-Disposition", getAttachmentStringFromFilename(filename)).build();
    }

    @GET
    @Path("download/npc/{id}")
    public Response getNonPlayingCharacterSupplement(@PathParam("id") Long id) {
        NonPlayingCharacterSupplement supplement = commandExecutor.execute(new GetNonPlayingCharacterSupplementCommand(id), getKey());
        if (supplement == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        String filename = supplement.getCharacter().getName() + " - Supplement-"
                + supplement.getIndex() + getSuffixFromMediaType(supplement.getMediaType());
        return Response.ok((Object) supplement.getContent()).type(supplement.getMediaType())
                .header("Content-Disposition", getAttachmentStringFromFilename(filename)).build();
    }

}
