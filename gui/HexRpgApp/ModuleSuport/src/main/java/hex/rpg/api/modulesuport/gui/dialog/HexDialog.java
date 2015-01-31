package hex.rpg.api.modulesuport.gui.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import org.openide.windows.WindowManager;

/**
 *
 * @author hln
 */
public abstract class HexDialog extends JDialog {

    private static final Frame MAIN_WINDOW = WindowManager.getDefault().getMainWindow();
    public enum Result {

        YES, NO, CANCEL, OK, HELP;
    }
    private Result result = Result.YES;

    public HexDialog(Frame owner, boolean modal) {
        super(owner, modal);
//        setIconImage(HexIcon.getImage("kephra"));
    }
    
    public HexDialog() {
        this(MAIN_WINDOW, true);
    }
    
    public static TextInputDialog showTextInputDialog(String title, String message, String resultText) {
        TextInputDialog dialog = new TextInputDialog(MAIN_WINDOW, title, true);
        dialog.setText(resultText);
        dialog.show(message);
        return dialog;
    }
    
    public static Result showYesNoCancelDialog(String title, String message) {
        YesNoCancelDialog dialog = new YesNoCancelDialog(MAIN_WINDOW, title, true);
        dialog.show(message);
        return dialog.getResult();
    }
    
    public static Result showYesNoDialog(String title, String message) {
        YesNoDialog dialog = new YesNoDialog(MAIN_WINDOW, title, true);
        dialog.show(message);
        return dialog.getResult();
    }
    
    public static void showErrorDialog(Exception exception) {
        ErrorDialog dialog = new ErrorDialog(MAIN_WINDOW, true);
        dialog.show(exception.getLocalizedMessage());
    }

    protected Result show(String message) {
        setLocationRelativeTo(getParent());
        setMessage(message);
        pack();
        setVisible(true);
        return getResult();
    }

    protected void setupKeyStrokes(JComponent... components) {
        AbstractAction enterAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterPressed();
            }
        };
        AbstractAction escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                escapePressed();
            }
        };
        for (JComponent c : components) {
            c.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
            c.getActionMap().put("enter", enterAction);
            c.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
            c.getActionMap().put("escape", escapeAction);
        }
    }

    protected void escapePressed() {
        setResult(Result.CANCEL);
    }

    protected void enterPressed() {
    }

    public abstract void setMessage(String message);

    protected void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

}
