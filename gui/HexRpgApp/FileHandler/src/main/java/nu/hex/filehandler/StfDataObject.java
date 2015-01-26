package nu.hex.filehandler;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_Stf_LOADER=Files of Stf"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Stf_LOADER",
        mimeType = "application/vnd.hex.rpg-stf",
        extension = {"stf", "STF"}
)
@DataObject.Registration(
        mimeType = "application/vnd.hex.rpg-stf",
        iconBase = "nu/hex/filehandler/stf.png",
        displayName = "#LBL_Stf_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/application/vnd.hex.rpg-stf/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class StfDataObject extends MultiDataObject {

    public StfDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("application/vnd.hex.rpg-stf", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_Stf_EDITOR",
            iconBase = "nu/hex/filehandler/stf.png",
            mimeType = "application/vnd.hex.rpg-stf",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Stf",
            position = 1000
    )
    @Messages("LBL_Stf_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

}
