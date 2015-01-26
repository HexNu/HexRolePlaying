package hex.rpg.app.campaign.node;

import hex.rpg.core.domain.campaign.Campaign;
import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author hln
 */
public class CampaignChildFactory extends Children.Keys<Campaign> {

    @Override
    protected Node[] createNodes(Campaign campaign) {
        return new Node[] {new CampaignNode(campaign)};
    }

    public void refreshList(List<Campaign> campaigns) {
        setKeys(campaigns);
    }
}
