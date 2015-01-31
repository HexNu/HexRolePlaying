package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.domain.story.AppEpisodeSupplement;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//EpisodeSupplmentEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "EpisodeSupplmentEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.EpisodeSupplmentEditorTopComponent")
public class EpisodeSupplementEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppEpisodeSupplement> {

    public EpisodeSupplementEditorTopComponent() {
        this(null);
    }

    public EpisodeSupplementEditorTopComponent(AppEpisodeSupplement entity) {
        super(entity);
        setIcon(HexIcon.getImage("file-picture-text"));
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
    }

    @Override
    protected boolean entitySpecificFieldsNeedsSaving() {
        return false;
    }
}
