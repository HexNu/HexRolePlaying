package hex.rpg.api.modulesuport.gui;

import java.io.IOException;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.util.Utilities;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author thomas
 */
public class SaveHandler extends AbstractSavable {

    private final Executor executor;
    private final InstanceContent instanceContent;
    private boolean singleSave = false;
    private boolean changed = false;
    private final Double seed = Math.random();
    private String displayName;

    public SaveHandler(InstanceContent instanceContent, Executor executor) {
        this.executor = executor;
        this.instanceContent = instanceContent;
        displayName = executor.toString();
    }

    @Override
    protected String findDisplayName() {
        return displayName;
    }

    @Override
    protected void handleSave() throws IOException {
        executor.executeSave();
    }

    public void setDisplayName(String newName) {
        displayName = newName;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
        setupSaveState();
    }

    public void allowSingleSave(boolean singleSave) {
        this.singleSave = singleSave;
        setupSaveState();
    }

    private void setupSaveState() {
        if (changed) {
            if (!REGISTRY.lookupAll(getClass()).contains(this)) {
                register();
            }
            if (singleSave) {
                if (!Utilities.actionsGlobalContext().lookupAll(getClass()).contains(this)) {
                    instanceContent.add(this);
                }
            } else {
                if (Utilities.actionsGlobalContext().lookupAll(getClass()).contains(this)) {
                    instanceContent.remove(this);
                }
            }
        } else {
            if (REGISTRY.lookupAll(getClass()).contains(this)) {
                unregister();
            }
            if (Utilities.actionsGlobalContext().lookupAll(getClass()).contains(this)) {
                instanceContent.remove(this);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SaveHandler)) {
            return false;
        }
        return seed.equals(((SaveHandler) o).seed);
    }

    @Override
    public int hashCode() {
        return seed.hashCode();
    }

    public static interface Executor {

        void executeSave();
    }
}
