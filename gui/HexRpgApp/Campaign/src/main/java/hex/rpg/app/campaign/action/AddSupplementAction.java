package hex.rpg.app.campaign.action;

import hex.rpg.api.modulesuport.action.HexAction;
import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import hex.rpg.api.modulesuport.gui.dialog.TextInputDialog;
import hex.rpg.app.campaign.node.AbstractRpgNode;
import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import hex.rpg.app.domain.character.AppNonPlayingCharacter;
import hex.rpg.app.domain.character.AppNonPlayingCharacterSupplement;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import hex.rpg.app.domain.story.AppStory;
import hex.rpg.app.domain.story.AppStorySupplement;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.openide.util.Exceptions;

/**
 *
 * @author hln
 */
public class AddSupplementAction<S extends Supplement> extends HexAction {

    private S supplement;
    private final AbstractRpgNode node;

    private final static Map<Class<? extends DomainEntity>, Class<? extends Supplement>> supplementMap = new HashMap<>();

    static {
        supplementMap.put(AppCampaign.class, AppCampaignSupplement.class);
        supplementMap.put(AppStory.class, AppStorySupplement.class);
        supplementMap.put(AppEpisode.class, AppEpisodeSupplement.class);
        supplementMap.put(AppNonPlayingCharacter.class, AppNonPlayingCharacterSupplement.class);
    }

    public AddSupplementAction(AbstractRpgNode node) {
        super("Add supplement");
        this.node = node;
    }

    @Override
    public void performAction(Object... params) {
        TextInputDialog titleDialog = HexDialog.showTextInputDialog("Add supplement","Supplement title", "");
        if (titleDialog.getResult().equals(HexDialog.Result.OK)) {
            final String title = titleDialog.getText();
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    boolean added = false;
                    try {
                        supplement = (S) supplementMap.get(node.getEntity().getClass()).newInstance();
                        supplement.setTitle(title);
                        if (node.getEntity() instanceof NarrativeEntity && supplementMap.containsKey(node.getEntity().getClass())) {
                            ((NarrativeEntity) node.getEntity()).addSupplement(supplement);
                            added = true;
                        } else if (node.getEntity() instanceof NonPlayingCharacter && supplementMap.containsKey(node.getEntity().getClass())) {
                            ((NonPlayingCharacter) node.getEntity()).addSupplement(supplement);
                            added = true;
                        }
                    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException ex) {
                        Logger.getLogger(AddSupplementAction.class.getName()).log(Level.WARNING, "Could not instanciate Supplement");
                    }
                    return added;
                }

                @Override
                protected void done() {
                    try {
                        if (get()) {
                            node.fireNodeChange();
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }.execute();
        }
    }
}
