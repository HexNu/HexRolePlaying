package hex.rpg.api.modulesuport.gui;

import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.TopComponent;

/**
 *
 * @author thomas
 * @param <T>
 */
public abstract class DataEditorTopComponent<T> extends TopComponent implements SaveHandler.Executor {

    private final SaveHandler saveHandler;
    private final InstanceContent instanceContent = new InstanceContent();

    public DataEditorTopComponent() {
        saveHandler = new SaveHandler(instanceContent, this);
        Lookup defaultLookup = new AbstractLookup(instanceContent);
        associateLookup(getExtendedLookup() == null ? defaultLookup : new ProxyLookup(defaultLookup, getExtendedLookup()));
    }

    protected Lookup getExtendedLookup() {
        return null;
    }

    private void setEditorIdString() {
        setHtmlDisplayName(!needsSaving() ? escapeLtAndGtInToString() : "<html><b>" + escapeLtAndGtInToString() + " *</b></html>");
        getSaveHandler().setDisplayName(toString());
    }

    private String escapeLtAndGtInToString() {
        return toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    @Override
    public final void setHtmlDisplayName(String htmlDisplayName) {
        super.setHtmlDisplayName(htmlDisplayName);
    }

    protected final SaveHandler getSaveHandler() {
        return saveHandler;
    }

    @Override
    public final boolean canClose() {
        if (isOpened() && needsSaving()) {
            HexDialog.Result saveResult = HexDialog.showYesNoCancelDialog("Unsaved data", "The window \"" + toString()
                    + "\" contains unsaved data. Do you want to save before continuing?");
            if (saveResult.equals(HexDialog.Result.CANCEL)) {
                return false;
            } else if (saveResult.equals(HexDialog.Result.YES)) {
                executeSave();
            }
        }
        getSaveHandler().setChanged(false);
        return true;
    }

    @Override
    protected void componentActivated() {
        getSaveHandler().allowSingleSave(true);
    }

    @Override
    protected void componentDeactivated() {
        getSaveHandler().allowSingleSave(false);
    }

    @Override
    protected void componentClosed() {
        getSaveHandler().setChanged(false);
    }

    protected void notifyChange() {
        getSaveHandler().setChanged(needsSaving());
        setEditorIdString();
    }

    @Override
    public void executeSave() {
        save();
        notifyChange();
    }

    public boolean proposeEditedEntity(T entity) {
        boolean result = canClose();
        if (result) {
            setEditedEntity(entity);
        }
        return result;
    }

    protected abstract void setEditedEntity(T entity);

    protected abstract boolean needsSaving();

    public abstract void save();

    public class ContentDocumentListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            notifyChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            notifyChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            notifyChange();
        }
    }

}
