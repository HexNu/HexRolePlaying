package hex.rpg.api.modulesuport.gui;

import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

/**
 *
 * @author thomas
 */
public class SimpleProgressViewer implements ProgressViewer {

    final ProgressHandle progress;
    final String text;

    public SimpleProgressViewer(String text) {
        this.text = text;
        progress = ProgressHandleFactory.createHandle(text);
        progress.setInitialDelay(200);
    }

    @Override
    public void start() {
        progress.start();
        progress.switchToIndeterminate();
        progress.setDisplayName(text);
    }

    @Override
    public void stop() {
        progress.finish();
    }

    @Override
    public void setPercentage(int percentage) {
    }
}
