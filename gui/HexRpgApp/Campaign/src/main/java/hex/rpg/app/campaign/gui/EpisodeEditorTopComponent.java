package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.campaign.gui.panel.EpisodeContentPanel;
import hex.rpg.app.domain.story.AppEpisode;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//EpisodeEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "EpisodeEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.EpisodeEditorTopComponent")
public class EpisodeEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppEpisode> {

    private EpisodeContentPanel contentPanel;
    private static final String CONTENT_TAB_LABEL = "Content";

    public EpisodeEditorTopComponent() {
        this(null);
    }

    public EpisodeEditorTopComponent(AppEpisode entity) {
        super(entity, null, Collections.singletonMap(CONTENT_TAB_LABEL, new EpisodeContentPanel()));
        setIcon(HexIcon.getImage("document-text"));
        contentPanel = (EpisodeContentPanel) getPanel(CONTENT_TAB_LABEL);
    }

    @Override
    void writeProperties(Properties p) {
        p.setProperty("version", "1.0");
    }

    @Override
    void readProperties(Properties p) {
        String version = p.getProperty("version");
    }

    @Override
    protected void populateEntitySpecificFields() {
        ((EpisodeContentPanel) getPanel(CONTENT_TAB_LABEL)).setContent(getEntity().getContent());
    }

    @Override
    protected boolean entitySpecificFieldsNeedsSaving() {
        return !Objects.equals(getEntity().getContent(), contentPanel.getContent());
    }
}
