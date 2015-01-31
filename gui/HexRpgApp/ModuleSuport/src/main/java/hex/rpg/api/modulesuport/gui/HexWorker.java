package hex.rpg.api.modulesuport.gui;

import hex.rpg.api.modulesuport.gui.dialog.HexDialog;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author thomas
 * @param <T>
 */
public abstract class HexWorker<T> extends SwingWorker<T, Void> {

    final ProgressViewer progressViewer;

    public HexWorker(String message) {
        progressViewer = new SimpleProgressViewer(message);
        progressViewer.start();
    }

    public HexWorker() {
        this("Processar data");
    }

    @Override
    protected final T doInBackground() throws Exception {
        return executeWork();
    }

    @Override
    protected final void done() {
        try {
            get();
            progressViewer.stop();
            updateGui();
        } catch (InterruptedException | ExecutionException ex) {
            progressViewer.stop();
            executeOnError(ex);
        }
    }

    protected T getWorkResult() {
        try {
            return get();
        } catch (InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected T executeWork() {
        return null;
    }

    protected void updateGui() {
    }

    protected void executeOnError(Exception ex) {
        HexDialog.showErrorDialog(ex);
    }
}
