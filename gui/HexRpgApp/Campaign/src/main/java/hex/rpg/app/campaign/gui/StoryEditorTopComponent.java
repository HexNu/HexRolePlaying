package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.domain.story.AppStory;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//StoryEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "StoryEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.StoryEditorTopComponent")
public class StoryEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppStory> {

    public StoryEditorTopComponent() {
        this(null);
    }

    public StoryEditorTopComponent(AppStory entity) {
        super(entity);
        setIcon(HexIcon.getImage("book"));
    }

    @Override
    protected void setupEntitySpecificListeners() {
    }

    @Override
    protected void populateEntitySpecificFields() {
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
