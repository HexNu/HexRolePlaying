package hex.rpg.stf.out;

import hex.rpg.stf.out.segment.DocumentBuilder;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.stf.out.segment.CampaignSegment;

/**
 *
 * @author hln
 */
public class SimpleTextFormatDocument {

    private final DocumentBuilder docBuilder;

    public SimpleTextFormatDocument() {
        this.docBuilder = new DocumentBuilder();
    }

    public void createCampaign(Campaign campaign) {
        new CampaignSegment(campaign, docBuilder).build();
    }

    public String getContent() {
        return docBuilder.toString();
    }
}
