package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.DataEditorTopComponent;
import hex.rpg.app.domain.story.AppEpisode;
import hex.rpg.core.domain.story.Episode;
import java.util.Objects;
import javax.swing.JComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.WindowManager;

@ConvertAsProperties(
        dtd = "-//hex.rpg.app.campaign.gui//EpisodeEditor//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = EpisodeEditorTopComponent.PREFERRED_ID,
        iconBase = "hex/rpg/app/campaign/gui/document-text.png",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.EpisodeEditorTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
//@TopComponent.OpenActionRegistration(
//        displayName = "#CTL_EpisodeEditorAction",
//        preferredID = EpisodeEditorTopComponent.PREFERRED_ID
//)
@Messages({
    "CTL_EpisodeEditorAction=EpisodeEditor",
    "CTL_EpisodeEditorTopComponent=EpisodeEditor Window",
    "HINT_EpisodeEditorTopComponent=This is a EpisodeEditor window"
})
public final class EpisodeEditorTopComponent extends DataEditorTopComponent<Episode> {

    public static final String PREFERRED_ID = "EpisodeEditorTopComponent";
    private Episode originalEpisode;
    private boolean initialized;

    public EpisodeEditorTopComponent() {
        this(new AppEpisode());
    }

    public EpisodeEditorTopComponent(Episode episode) {
        initComponents();
        setEditedEntity(episode);
        setName(episode.getTitle());
        setToolTipText(Bundle.HINT_EpisodeEditorTopComponent());
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        episodeTabbedPane = new javax.swing.JTabbedPane();
        metaDataPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        titleTextField = new javax.swing.JTextField();
        indexLabel = new javax.swing.JLabel();
        indexSpinner = new javax.swing.JSpinner();
        shortDescriptionLabel = new javax.swing.JLabel();
        shortDescriptionScrollPane = new javax.swing.JScrollPane();
        shortDescriptionTextArea = new javax.swing.JTextArea();
        descriptionPanel = new javax.swing.JPanel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        refereeInfoPanel = new javax.swing.JPanel();
        refereeInfoScrollPane = new javax.swing.JScrollPane();
        refereeInfoTextArea = new javax.swing.JTextArea();
        refereeNotesPanel = new javax.swing.JPanel();
        refereeNotesScrollPane = new javax.swing.JScrollPane();
        refereeNotesTextArea = new javax.swing.JTextArea();
        contentPanel = new javax.swing.JPanel();
        contentScrollPane = new javax.swing.JScrollPane();
        contentTextArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        episodeTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.titleLabel.text")); // NOI18N

        titleTextField.setText(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.titleTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(indexLabel, org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.indexLabel.text")); // NOI18N

        indexSpinner.setModel(new javax.swing.SpinnerNumberModel());

        org.openide.awt.Mnemonics.setLocalizedText(shortDescriptionLabel, org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.shortDescriptionLabel.text")); // NOI18N

        shortDescriptionTextArea.setColumns(20);
        shortDescriptionTextArea.setLineWrap(true);
        shortDescriptionTextArea.setRows(3);
        shortDescriptionTextArea.setWrapStyleWord(true);
        shortDescriptionScrollPane.setViewportView(shortDescriptionTextArea);

        javax.swing.GroupLayout metaDataPanelLayout = new javax.swing.GroupLayout(metaDataPanel);
        metaDataPanel.setLayout(metaDataPanelLayout);
        metaDataPanelLayout.setHorizontalGroup(
            metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metaDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shortDescriptionScrollPane)
                    .addGroup(metaDataPanelLayout.createSequentialGroup()
                        .addGroup(metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(shortDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(indexLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleTextField)
                            .addGroup(metaDataPanelLayout.createSequentialGroup()
                                .addComponent(indexSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 230, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        metaDataPanelLayout.setVerticalGroup(
            metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(metaDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(metaDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(indexLabel)
                    .addComponent(indexSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shortDescriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shortDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(302, Short.MAX_VALUE))
        );

        episodeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.metaDataPanel.TabConstraints.tabTitle"), metaDataPanel); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        episodeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.descriptionPanel.TabConstraints.tabTitle"), descriptionPanel); // NOI18N

        refereeInfoTextArea.setColumns(20);
        refereeInfoTextArea.setLineWrap(true);
        refereeInfoTextArea.setRows(5);
        refereeInfoTextArea.setWrapStyleWord(true);
        refereeInfoScrollPane.setViewportView(refereeInfoTextArea);

        javax.swing.GroupLayout refereeInfoPanelLayout = new javax.swing.GroupLayout(refereeInfoPanel);
        refereeInfoPanel.setLayout(refereeInfoPanelLayout);
        refereeInfoPanelLayout.setHorizontalGroup(
            refereeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(refereeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refereeInfoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );
        refereeInfoPanelLayout.setVerticalGroup(
            refereeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(refereeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refereeInfoScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        episodeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.refereeInfoPanel.TabConstraints.tabTitle"), refereeInfoPanel); // NOI18N

        refereeNotesTextArea.setColumns(20);
        refereeNotesTextArea.setLineWrap(true);
        refereeNotesTextArea.setRows(5);
        refereeNotesTextArea.setWrapStyleWord(true);
        refereeNotesScrollPane.setViewportView(refereeNotesTextArea);

        javax.swing.GroupLayout refereeNotesPanelLayout = new javax.swing.GroupLayout(refereeNotesPanel);
        refereeNotesPanel.setLayout(refereeNotesPanelLayout);
        refereeNotesPanelLayout.setHorizontalGroup(
            refereeNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(refereeNotesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refereeNotesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );
        refereeNotesPanelLayout.setVerticalGroup(
            refereeNotesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(refereeNotesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refereeNotesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        episodeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.refereeNotesPanel.TabConstraints.tabTitle"), refereeNotesPanel); // NOI18N

        contentTextArea.setColumns(20);
        contentTextArea.setLineWrap(true);
        contentTextArea.setRows(5);
        contentTextArea.setWrapStyleWord(true);
        contentScrollPane.setViewportView(contentTextArea);

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addContainerGap())
        );

        episodeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(EpisodeEditorTopComponent.class, "EpisodeEditorTopComponent.contentPanel.TabConstraints.tabTitle"), contentPanel); // NOI18N

        add(episodeTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPane;
    private javax.swing.JTextArea contentTextArea;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JTabbedPane episodeTabbedPane;
    private javax.swing.JLabel indexLabel;
    private javax.swing.JSpinner indexSpinner;
    private javax.swing.JPanel metaDataPanel;
    private javax.swing.JPanel refereeInfoPanel;
    private javax.swing.JScrollPane refereeInfoScrollPane;
    private javax.swing.JTextArea refereeInfoTextArea;
    private javax.swing.JPanel refereeNotesPanel;
    private javax.swing.JScrollPane refereeNotesScrollPane;
    private javax.swing.JTextArea refereeNotesTextArea;
    private javax.swing.JLabel shortDescriptionLabel;
    private javax.swing.JScrollPane shortDescriptionScrollPane;
    private javax.swing.JTextArea shortDescriptionTextArea;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField titleTextField;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        requestActive();
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    @Override
    public void addNotify() {
        super.addNotify();
        JComponent parent = (JComponent) getParent();
        parent.setOpaque(false);
    }

    private void populateFields() {
        titleTextField.setText(originalEpisode.getTitle());
        indexSpinner.setValue(originalEpisode.getIndex());
        shortDescriptionTextArea.setText(originalEpisode.getShortDescription());
        descriptionTextArea.setText(originalEpisode.getDescription());
        contentTextArea.setText(originalEpisode.getContent());
        refereeInfoTextArea.setText(originalEpisode.getRefereeInfo());
        refereeNotesTextArea.setText(originalEpisode.getRefereeNotes());
    }

    @Override
    protected void setEditedEntity(Episode episode) {
        initialized = false;
        originalEpisode = episode;
        populateFields();
        initialized = true;
    }

    @Override
    protected boolean needsSaving() {
        if (initialized == false) {
            return false;
        }
        if (!Objects.equals(titleTextField.getText(), originalEpisode.getTitle())) {
            return true;
        }
        if (!Objects.equals(indexSpinner.getValue(), originalEpisode.getIndex())) {
            return true;
        }
        if (!Objects.equals(shortDescriptionTextArea.getText(), originalEpisode.getShortDescription())) {
            return true;
        }
        if (!Objects.equals(shortDescriptionTextArea.getText(), originalEpisode.getDescription())) {
            return true;
        }
        if (!Objects.equals(refereeInfoTextArea.getText(), originalEpisode.getRefereeInfo())) {
            return true;
        }
        if (!Objects.equals(refereeNotesTextArea.getText(), originalEpisode.getRefereeNotes())) {
            return true;
        }
        if (!Objects.equals(contentTextArea.getText(), originalEpisode.getContent())) {
            return true;
        }
        return true;
    }

    @Override
    public void save() {
    }
}
