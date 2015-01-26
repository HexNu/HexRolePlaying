package nu.hex.filehandler.node;

import hex.rpg.stf.ContentType;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class StffNodeChildFactory extends ChildFactory<TextSegment> {

    private final static Map<ContentType, Class<? extends AbstractStffNode>> nodeMap = new HashMap<>();
    private final TextSegment segment;

    static {
        nodeMap.put(ContentType.CAMPAIGN, CampaignNode.class);
        nodeMap.put(ContentType.CAMPAIGN_SUPPLEMENT, CampaignSupplementNode.class);
        nodeMap.put(ContentType.STORY, StoryNode.class);
        nodeMap.put(ContentType.STORY_SUPPLEMENT, StorySupplementNode.class);
        nodeMap.put(ContentType.EPISODE, EpisodeNode.class);
        nodeMap.put(ContentType.EPISODE_SUPPLEMENT, EpisodeSupplementNode.class);
        nodeMap.put(ContentType.NON_PLAYING_CHARACTER, NonPlayingCharacterNode.class);
        nodeMap.put(ContentType.NON_PLAYING_CHARACTER_SUPPLEMENT, NonPlayingCharacterSupplementNode.class);
    }
    private final Lookup lookup;

    public StffNodeChildFactory(TextSegment segment, Lookup lookup) {
        this.segment = segment;
        this.lookup = lookup;
    }

    @Override
    protected boolean createKeys(List<TextSegment> list) {
        if (segment.hasChildren()) {
            list.addAll(segment.getChildren());
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(TextSegment segment) {
        ContentType key = segment.getType();
        if (key != null) {
            try {
                return nodeMap.get(key).getConstructor(TextSegment.class, Lookup.class).newInstance(segment, lookup);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException |
                    InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                Logger.getLogger(StffNodeChildFactory.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
        }
        return null;
    }
}
