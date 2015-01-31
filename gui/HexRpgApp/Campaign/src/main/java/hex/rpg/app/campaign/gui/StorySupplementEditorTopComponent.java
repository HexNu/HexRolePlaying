package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.campaign.gui.panel.SupplementImageContentPanel;
import hex.rpg.app.domain.story.AppStorySupplement;
import java.util.Collections;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//StorySupplementEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "StorySupplementEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.StorySupplementEditorTopComponent")
public class StorySupplementEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppStorySupplement> {

    SupplementImageContentPanel contentPanel;
    private static final String CONTENT_TAB_LABEL = "Content";

    public StorySupplementEditorTopComponent() {
        this(new AppStorySupplement());
    }

    public StorySupplementEditorTopComponent(AppStorySupplement entity) {
        super(entity, null, Collections.singletonMap(CONTENT_TAB_LABEL, new SupplementImageContentPanel()));
        setIcon(HexIcon.getImage("file-picture-text"));
        contentPanel = (SupplementImageContentPanel) getPanel(CONTENT_TAB_LABEL);
    }

    @Override
    protected void setupEntitySpecificListeners() {
    }

    @Override
    protected void populateEntitySpecificFields() {
        ((SupplementImageContentPanel) getPanel(CONTENT_TAB_LABEL)).setContent(getEntity().getContentAsByteArray());
    }

    @Override
    protected boolean entitySpecificFieldsNeedsSaving() {
        return false;
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
    public void save() {
    }
}
