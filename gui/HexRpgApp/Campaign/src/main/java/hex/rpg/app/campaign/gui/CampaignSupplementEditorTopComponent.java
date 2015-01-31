package hex.rpg.app.campaign.gui;

import hex.rpg.api.modulesuport.gui.HexIcon;
import hex.rpg.app.campaign.gui.panel.SupplementImageContentPanel;
import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import java.util.Collections;
import java.util.Properties;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.windows.TopComponent;

/**
 *
 * @author hln
 */
@ConvertAsProperties(dtd = "-//hex.rpg.app.campaign.gui//CampaignSupplementEditorTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "CampaignSupplementEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "hex.rpg.app.campaign.gui.CampaignSupplementEditorTopComponent")
public class CampaignSupplementEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppCampaignSupplement> {
    
    SupplementImageContentPanel contentPanel;
    private static final String CONTENT_TAB_LABEL = "Content";

    public CampaignSupplementEditorTopComponent() {
        this(new AppCampaignSupplement());
    }

    public CampaignSupplementEditorTopComponent(AppCampaignSupplement entity) {
        super(entity, null, Collections.singletonMap(CONTENT_TAB_LABEL, new SupplementImageContentPanel()));
        setIcon(HexIcon.getImage("file-picture-text"));
        contentPanel = (SupplementImageContentPanel) getPanel(CONTENT_TAB_LABEL);
    }

    @Override
    void writeProperties(Properties p) {
    }

    @Override
    void readProperties(Properties p) {
    }

    @Override
    protected void populateEntitySpecificFields() {
        ((SupplementImageContentPanel) getPanel(CONTENT_TAB_LABEL)).setContent(getEntity().getContentAsByteArray());
    }

    @Override
    protected boolean entitySpecificFieldsNeedsSaving() {
        return false;
    }
}
