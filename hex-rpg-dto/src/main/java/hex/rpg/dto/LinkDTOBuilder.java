package hex.rpg.dto;

import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.NonPlayingCharacterSupplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

/**
 * Created 2014-sep-26
 *
 * @author jep
 */
public class LinkDTOBuilder {

    private final String baseUri;

    public LinkDTOBuilder(String baseUri) {
        this.baseUri = baseUri;
    }

    protected URI createURI(String append) {
        return UriBuilder.fromPath(appendToBaseUri(append)).build();
    }

    protected String appendToBaseUri(String value) {
        StringBuilder sb = new StringBuilder(baseUri);
        sb.append(value);
        return sb.toString();
    }

    public LinkDTO createLink(String rel, String path) {
        return new LinkDTO(rel, createURI(path));
    }
    
    public LinkDTO createStoryLink(String rel, Story story) {
        String path = buildPath(story.getCampaign().getId(), story.getId());
        LinkDTO result = createLink(rel, path);
        return result;
    }
    
    public LinkDTO createEpisodeLink(String rel, Episode episode) {
        String path = buildPath(episode.getStory().getCampaign().getId(), episode.getStory().getId(), episode.getId());
        LinkDTO result = createLink(rel, path);
        return result;
    }
    
    public LinkDTO createSupplementDownloadLink(Supplement supplement) {
        String path = "supplement/download/";
        if (supplement instanceof EpisodeSupplement) {
            path += "episode/";
        } else if (supplement instanceof StorySupplement) {
            path += "story/";
        } else if (supplement instanceof CampaignSupplement){
            path += "campaign/";
        } else if (supplement instanceof NonPlayingCharacterSupplement){
            path += "npc/";
        }
        path += supplement.getId();
        return createLink("download", path);
    }

    public LinkDTO createPortraitDownloadLink(NonPlayingCharacter character) {
        String path = "character/" + character.getCampaign().getId() + "/portrait";
        return createLink("download", path);
    }
    
    private String buildPath(Long... parts) {
        StringBuilder builder = new StringBuilder("campaign");
        for (Long l : parts) {
            builder.append("/").append(l);
        }
        return builder.toString();
    }
}
