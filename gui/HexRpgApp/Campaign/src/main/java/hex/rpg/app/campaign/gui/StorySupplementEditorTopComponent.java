package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.domain.story.AppStorySupplement;
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

    public StorySupplementEditorTopComponent() {
        this(null);
    }

    public StorySupplementEditorTopComponent(AppStorySupplement entity) {
        super(entity);
        setIcon(HexIcon.getImage("file-picture-text"));
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
}
