package hex.rpg.app.campaign.node;

import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author hln
 */
public class RootNodeChildFactory extends ChildFactory<Campaign> {

    private final List<Campaign> campaigns;

    public RootNodeChildFactory(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    protected boolean createKeys(List<Campaign> list) {
        list.addAll(campaigns);
        return true;
    }

    @Override
    protected Node createNodeForKey(Campaign key) {
        return new CampaignNode((AppCampaign) key);
    }
}
