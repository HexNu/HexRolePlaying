package nu.hex.filehandler;

import java.io.IOException;
import java.util.List;
import nu.hex.filehandler.node.CampaignNode;
import nu.hex.filehandler.node.TextSegment;
import nu.hex.filehandler.node.TextSegmentTreeBuilder;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_Stff_LOADER=Files of Stff"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Stff_LOADER",
        mimeType = "text/vnd.hex.rpg-stff",
        extension = {"stff", "STFF"}
)
@DataObject.Registration(
        mimeType = "text/vnd.hex.rpg-stff",
        iconBase = "nu/hex/filehandler/stff.png",
        displayName = "#LBL_Stff_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/vnd.hex.rpg-stff/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class StffDataObject extends MultiDataObject {

    public StffDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/vnd.hex.rpg-stff", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_Stff_EDITOR",
            iconBase = "nu/hex/filehandler/stff.png",
            mimeType = "text/vnd.hex.rpg-stff",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Stff",
            position = 1000
    )
    @Messages("LBL_Stff_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataNode(this, Children.create(new StffFileChildFactory(this, getLookup()), true), getLookup());
    }

    private static class StffFileChildFactory extends ChildFactory<TextSegment> {

        private TextSegmentTreeBuilder treeBuilder;
        private final Lookup lookup;

        public StffFileChildFactory(StffDataObject dataObject, Lookup lookup) {
            try {
                FileObject fileObject = dataObject.getPrimaryFile();
                treeBuilder = new TextSegmentTreeBuilder(fileObject.asText());
            } catch (IOException ex) {
                treeBuilder = null;
                Exceptions.printStackTrace(ex);
            }
            this.lookup = lookup;
        }

        @Override
        protected boolean createKeys(List<TextSegment> list) {
            if (treeBuilder != null) {
                list.add(treeBuilder.getRootSegment());
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(TextSegment key) {
            return new CampaignNode(key, lookup);
        }
    }
}
