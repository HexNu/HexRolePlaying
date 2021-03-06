package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.AbstractEditEntityAction;
import hex.rpg.app.domain.AppDomainEntity;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.character.AppNonPlayingCharacter;
import hex.rpg.app.domain.character.AppNonPlayingCharacterSupplement;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author hln
 */
public class RpgNodeChildFactory<T> extends ChildFactory<AppDomainEntity> {

    private final T entity;
    private static final Map<Class<? extends AppDomainEntity>, Class<? extends AbstractRpgNode>> nodeMap = new HashMap();
    private final Observer observer = new Observer() {

        @Override
        public void update(Observable o, Object arg) {
            refresh(false);
        }
    };

    static {
        nodeMap.put(AppCampaign.class, CampaignNode.class);
        nodeMap.put(AppCampaignSupplement.class, CampaignSupplementNode.class);
        nodeMap.put(AppStory.class, StoryNode.class);
        nodeMap.put(AppStorySupplement.class, StorySupplementNode.class);
        nodeMap.put(AppEpisode.class, EpisodeNode.class);
        nodeMap.put(AppEpisodeSupplement.class, EpisodeSupplementNode.class);
        nodeMap.put(AppNonPlayingCharacter.class, CharacterNode.class);
        nodeMap.put(AppNonPlayingCharacterSupplement.class, CharacterSupplementNode.class);
    }

    public RpgNodeChildFactory(T entity) {
        this.entity = entity;
        CampaignNode.getCommonObservable().addObserver(observer);
        CampaignSupplementNode.getCommonObservable().addObserver(observer);
        StoryNode.getCommonObservable().addObserver(observer);
        StorySupplementNode.getCommonObservable().addObserver(observer);
        EpisodeNode.getCommonObservable().addObserver(observer);
        EpisodeSupplementNode.getCommonObservable().addObserver(observer);
    }

    @Override
    protected boolean createKeys(List<AppDomainEntity> list) {
        if (entity instanceof Campaign) {
            addChildren((AppCampaign) entity, list);
        } else if (entity instanceof Story) {
            addChildren((AppStory) entity, list);
        } else if (entity instanceof Episode) {
            addChildren((AppEpisode) entity, list);
        } else if (entity instanceof NonPlayingCharacter) {
            addChildren((AppNonPlayingCharacter) entity, list);
        }
        return true;
    }

    private void addChildren(AppCampaign campaign, List<AppDomainEntity> list) {
        campaign.getSupplements().stream().forEach((supplement) -> {
            list.add((AppCampaignSupplement) supplement);
        });
        campaign.getStories().stream().forEach((story) -> {
            list.add((AppStory) story);
        });
        campaign.getCharacters().stream().forEach((character) -> {
            list.add((AppNonPlayingCharacter) character);
        });
    }

    private void addChildren(AppStory story, List<AppDomainEntity> list) {
        story.getSupplements().stream().forEach((supplement) -> {
            list.add((AppStorySupplement) supplement);
        });
        story.getEpisodes().stream().forEach((episode) -> {
            list.add((AppEpisode) episode);
        });
    }

    private void addChildren(AppEpisode episode, List<AppDomainEntity> list) {
        episode.getSupplements().stream().forEach((supplement) -> {
            list.add((AppEpisodeSupplement) supplement);
        });
    }

    private void addChildren(AppNonPlayingCharacter character, List<AppDomainEntity> list) {
        character.getSupplements().stream().forEach((supplement) -> {
            list.add((AppNonPlayingCharacterSupplement) supplement);
        });
    }

    @Override
    protected Node createNodeForKey(AppDomainEntity key) {
        try {
            return nodeMap.get(key.getClass()).getConstructor(key.getClass()).newInstance(key);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(AbstractEditEntityAction.class.getName()).log(Level.SEVERE, "Could not instanciate the node: {0}", ex.getMessage());
        }
        return null;
    }
}
