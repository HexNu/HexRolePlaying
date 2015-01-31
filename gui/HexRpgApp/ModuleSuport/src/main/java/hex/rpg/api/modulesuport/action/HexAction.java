package hex.rpg.api.modulesuport.action;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author hln
 */
public abstract class HexAction extends AbstractAction {

    private final String name;

    public HexAction() {
        this("");
    }

    public HexAction(String name) {
        super(name);
        this.name = name;
    }

    public abstract void performAction(Object... params);

    @Override
    public void actionPerformed(ActionEvent e) {
        performAction(e);
    }

    public String getName() {
        return name;
    }
}
