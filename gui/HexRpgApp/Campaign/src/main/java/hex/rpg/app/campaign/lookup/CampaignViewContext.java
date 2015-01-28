package hex.rpg.app.campaign.lookup;

import hex.rpg.app.domain.campaign.AppCampaign;
import java.util.Collections;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author hln
 */
public class CampaignViewContext {

    private static final InstanceContent campaignViewContent = new InstanceContent();
    private static final Lookup campaignViewLookup = new AbstractLookup(campaignViewContent);

    public static Lookup getLookup() {
        return campaignViewLookup;
    }

    public static void publishCampaign(AppCampaign campaign) {
        campaignViewContent.set(campaign == null ? Collections.EMPTY_LIST : Collections.singletonList(campaign), null);
    }
}
