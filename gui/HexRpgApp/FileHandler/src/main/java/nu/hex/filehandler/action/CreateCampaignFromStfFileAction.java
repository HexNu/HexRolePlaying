package nu.hex.filehandler.action;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.stf.ParseZippedStfStream;
import hex.rpg.stf.Parsing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;
import nu.hex.filehandler.StfDataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(
        category = "File",
        id = "nu.hex.filehandler.action.CreateCampaignFromStfFileAction"
)
@ActionRegistration(
        iconBase = "nu/hex/filehandler/action/stf.png",
        displayName = "#CTL_CreateCampaignFromStfFileAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1400, separatorAfter = 1500),
    @ActionReference(path = "Loaders/application/vnd.hex.rpg-stf/Actions", position = 0, separatorAfter = 50)
})
@Messages("CTL_CreateCampaignFromStfFileAction=Create campaign from file")
public final class CreateCampaignFromStfFileAction implements ActionListener {

    private final StfDataObject context;
    private final Map<String, String> processingInstructionsMap = new HashMap<>();

    public CreateCampaignFromStfFileAction(StfDataObject context) {
        this.context = context;
    }

    public CreateCampaignFromStfFileAction() {
        this(null);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String selectedFilePath = null;
        FileObject fileObject = context.getPrimaryFile();
        if (fileObject != null) {
            selectedFilePath = FileUtil.getFileDisplayName(fileObject);
        } else {
            JFileChooser stfFileChooser = new JFileChooser(new File(System.getProperty("user.home")), FileSystemView.getFileSystemView());
            stfFileChooser.setDialogTitle("Select STF file");
            stfFileChooser.setToolTipText("<html>"
                    + "<b>Select STF file.</b><br>"
                    + "This file will be imported as a Campaign.</html>");
            stfFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        }
        if (selectedFilePath != null) {
            final String stfFilePath = selectedFilePath;
            new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    String basepath = null;
                    extractProcessingInstructions(stfFilePath);
                    if (processingInstructionsMap.containsKey("basepath")) {
                        basepath = processingInstructionsMap.get("basepath").replaceAll("\"", "");
                    }
                    return basepath;
                }

                @Override
                protected void done() {
                    try {
                        File campaignDir = new File(System.getProperty("user.home"));
                        if (get() != null) {
                            campaignDir = new File(get());
                            campaignDir.mkdirs();
                        }
                        JFileChooser fileChooser = new JFileChooser(campaignDir.getParent(), FileSystemView.getFileSystemView());
                        fileChooser.setDialogTitle("Select root directory for the campaign");
                        fileChooser.setSelectedFile(campaignDir);
                        fileChooser.setToolTipText("<html>"
                                + "<b>Choose a root directory for the campaign.</b><br>"
                                + "This will also be the target directory for unpacking the content of <br>"
                                + stfFilePath
                                + "</html>");
                        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(WindowManager.getDefault().getMainWindow())) {
                            try {
                                ParseZippedStfStream parser = new ParseZippedStfStream(new FileInputStream(stfFilePath), fileChooser.getSelectedFile().getAbsolutePath());
                                Campaign c = parser.parse();
                            } catch (FileNotFoundException ex) {
                                Exceptions.printStackTrace(ex);
                            }
                        }
                    } catch (InterruptedException | ExecutionException ex) {
                        Logger.getLogger(CreateCampaignFromStfFileAction.class.getName()).log(Level.SEVERE, ex.getMessage());
                    }
                }
            }.execute();
        }

    }

    private void extractProcessingInstructions(String stfFilePath) {
        try {
            final ZipFile zipFile = new ZipFile(stfFilePath);
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = entries.nextElement();
                if (!zipEntry.isDirectory()) {
                    if (zipEntry.getName().equals(".PROC") || zipEntry.getName().endsWith(".stff")) {
                        readLinesFromFiles(zipFile, zipEntry);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateCampaignFromStfFileAction.class.getName()).log(Level.SEVERE, "Could not find the file \"{0}\"!", stfFilePath);
        } catch (IOException ex) {
            Logger.getLogger(CreateCampaignFromStfFileAction.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }

    private void readLinesFromFiles(final ZipFile zipFile, final ZipEntry zipEntry) throws IOException, UnsupportedEncodingException {
        InputStream input = zipFile.getInputStream(zipEntry);
        String currentLine;
        String key;
        String value;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        while ((currentLine = reader.readLine()) != null) {
            String line;
            if (currentLine.matches(Parsing.REGEX_PROCESSING_INSTRUCTION)) {
                line = currentLine.substring(2).trim();
                key = line.substring(0, line.indexOf(" "));
                value = line.substring(line.indexOf(" ") + 1);
                processingInstructionsMap.put(key, value);
            }
        }
    }
}
