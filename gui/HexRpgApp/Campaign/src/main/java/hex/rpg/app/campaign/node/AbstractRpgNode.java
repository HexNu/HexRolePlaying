package hex.rpg.app.campaign.node;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.domain.AppDomainEntity;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.character.AppNonPlayingCharacter;
import hex.rpg.app.domain.character.AppNonPlayingCharacterSupplement;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author hln
 */
public abstract class AbstractRpgNode<T extends AppDomainEntity> extends AbstractNode {

    private final T entity;
    private String iconName;
    private final List<Action> actionList = new ArrayList<>();
    public static final Action CONTEXT_DELIMITER = null;
    private final static Map<Class<? extends AppDomainEntity>, String> iconMap = new HashMap<>();

    static {
        iconMap.put(AppCampaign.class, "library");
        iconMap.put(AppCampaignSupplement.class, "file-picture-text");
        iconMap.put(AppStory.class, "book");
        iconMap.put(AppStorySupplement.class, "file-picture-text");
        iconMap.put(AppEpisode.class, "document-text");
        iconMap.put(AppEpisodeSupplement.class, "file-picture-text");
        iconMap.put(AppNonPlayingCharacter.class, "man");
        iconMap.put(AppNonPlayingCharacterSupplement.class, "file-picture-text");
    }

    public AbstractRpgNode(T entity) {
        super(isLeafNode(entity) ? Children.LEAF : Children.create(new RpgNodeChildFactory<>(entity), true));
        this.entity = entity;
        init();
    }

    private void init() {

        setDisplayName(entity.getName());
        setIcon(iconMap.get(entity.getClass()));
        setShortDescription(createShortDescription());
        setup();
    }

    private String createShortDescription() {
        StringBuilder builder = new StringBuilder("<html>");
        builder.append("<div style=\"background: #ffffee; color: #003300; padding: 10px; width: 300px;\">");
        builder.append("<b>").append(entity.getClass().getSimpleName().replace("App", "")).append(": ")
                .append(entity.getName()).append("</b>");
        if (entity.getShortDescription() != null && !entity.getShortDescription().equals("")) {
            builder.append("<br/>").append(entity.getShortDescription());
        }
        builder.append("</div>");
        return builder.append("</html>").toString();
    }

    /**
     * typically for setName setIcon addAction
     */
    protected abstract void setup();

    public T getEntity() {
        return entity;
    }

    protected void setIcon(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public Image getIcon(int type) {
        return new HexIcon(iconName).getImage();
    }

    @Override
    public Action[] getActions(boolean context) {
        return actionList.toArray(new Action[actionList.size()]);
    }

    protected void addAction(Action action) {
        if (action == CONTEXT_DELIMITER) {
            actionList.add(CONTEXT_DELIMITER);
        } else {
            boolean add = true;
            for (Action listedAction : actionList) {
                if (listedAction != CONTEXT_DELIMITER
                        && listedAction.getClass().getName().equals(action.getClass().getName())) {
                    add = false;
                    break;
                }
            }
            if (add) {
                actionList.add(action);
            }
        }
    }

    protected void setActions(Collection<Action> actions) {
        actionList.addAll(actions);
    }

    private static boolean isLeafNode(DomainEntity entity) {
        if (entity instanceof Supplement) {
            return true;
        } else if (entity instanceof Episode) {
            return !((Episode) entity).hasSupplements();
        } else if (entity instanceof Story) {
            Story s = (Story) entity;
            return !s.hasSupplements() && s.getEpisodes().isEmpty();
        } else if (entity instanceof NonPlayingCharacter) {
            return !((NonPlayingCharacter) entity).hasSupplements();
        }
        return false;
    }
}
