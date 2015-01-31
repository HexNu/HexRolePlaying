package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.domain.campaign.AppCampaign;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//CampaignEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "CampaignEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.CampaignEditorTopComponent")
public class CampaignEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppCampaign> {

    public CampaignEditorTopComponent() {
        this(new AppCampaign());
    }

    public CampaignEditorTopComponent(AppCampaign entity) {
        super(entity);
        setIcon(HexIcon.getImage("library"));
    }

    @Override
    protected void setupEntitySpecificListeners() {
    }

    @Override
    void writeProperties(Properties p) {
    }

    @Override
    void readProperties(Properties p) {
    }

    @Override
    protected void populateEntitySpecificFields() {
    }

    @Override
    protected boolean entitySpecificFieldsNeedsSaving() {
        return false;
    }

    @Override
    public void save() {

    }

}
