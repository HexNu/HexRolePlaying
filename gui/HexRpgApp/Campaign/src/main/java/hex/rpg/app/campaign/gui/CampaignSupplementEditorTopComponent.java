package hex.rpg.app.campaign.gui;

import hex.rpg.app.domain.campaign.AppCampaignSupplement;
import java.util.Properties;

/**
 *
 * @author hln
 */
public class CampaignSupplementEditorTopComponent extends AbstractNarrativeEntityEditorTopComponent<AppCampaignSupplement> {

    public CampaignSupplementEditorTopComponent() {
        this(new AppCampaignSupplement());
    }

    public CampaignSupplementEditorTopComponent(AppCampaignSupplement entity) {
        super(entity);
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
}
