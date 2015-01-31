package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.DataEditorTopComponent;
import hex.rpg.app.campaign.gui.AbstractNarrativeEntityEditorTopComponent;
import hex.rpg.app.campaign.gui.CampaignEditorTopComponent;
import hex.rpg.app.campaign.gui.CampaignSupplementEditorTopComponent;
import hex.rpg.app.campaign.gui.EpisodeEditorTopComponent;
import hex.rpg.app.campaign.gui.EpisodeSupplementEditorTopComponent;
import hex.rpg.app.campaign.gui.StoryEditorTopComponent;
import hex.rpg.app.campaign.gui.StorySupplementEditorTopComponent;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.NarrativeEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author hln
 */
public class AbstractEditEntityAction<T extends NarrativeEntity> extends HexAction {

    private final T entity;
    private static final Map<Class<? extends NarrativeEntity>, Class<? extends AbstractNarrativeEntityEditorTopComponent>> editorMap = new HashMap<>();

    static {
        editorMap.put(AppCampaign.class, CampaignEditorTopComponent.class);
        editorMap.put(AppCampaignSupplement.class, CampaignSupplementEditorTopComponent.class);
        editorMap.put(AppEpisode.class, EpisodeEditorTopComponent.class);
        editorMap.put(AppEpisodeSupplement.class, EpisodeSupplementEditorTopComponent.class);
        editorMap.put(AppStory.class, StoryEditorTopComponent.class);
        editorMap.put(AppStorySupplement.class, StorySupplementEditorTopComponent.class);
    }

    public AbstractEditEntityAction(T entity) {
        super("Edit " + entity.getClass().getSimpleName().replace("App", ""));
        this.entity = entity;
    }

    @Override
    public void performAction(Object... params) {
        boolean isAlreadyOpened = false;
        TopComponent[] openedTopComponents = WindowManager.getDefault().getOpenedTopComponents(WindowManager.getDefault().findMode("editor"));
        if (openedTopComponents != null) {
            for (TopComponent tc : openedTopComponents) {
                if (tc instanceof DataEditorTopComponent && entity.getTitle().equals(((DataEditorTopComponent) tc).toString())) {
                    isAlreadyOpened = true;
                    tc.requestFocus();
                    tc.requestActive();
                    break;
                }
            }
        }
        if (!isAlreadyOpened) {
            try {
                TopComponent entityEditor = editorMap.get(entity.getClass()).getConstructor(entity.getClass()).newInstance(entity);
                entityEditor.open();
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(AbstractEditEntityAction.class.getName()).log(Level.SEVERE, "Could not instanciate the editor: {0}", ex.getMessage());
            }
        }
    }
}
