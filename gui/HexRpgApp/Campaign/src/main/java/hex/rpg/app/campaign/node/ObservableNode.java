package hex.rpg.app.campaign.node;

import java.awt.EventQueue;
import java.util.Observable;

/**
 *
 * @author hln
 */
public class ObservableNode extends Observable {

    protected void notify(final Object object) {
        EventQueue.invokeLater(() -> {
            setChanged();
            notifyObservers(object);
        });
    }
}
