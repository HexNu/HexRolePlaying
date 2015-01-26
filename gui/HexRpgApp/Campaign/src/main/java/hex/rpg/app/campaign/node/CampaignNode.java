package hex.rpg.app.campaign.node;

import hex.rpg.core.domain.campaign.Campaign;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author hln
 */
public class CampaignNode extends AbstractNode {
    private final Campaign campaign;

    public CampaignNode(Campaign campaign) {
        super(Children.LEAF);
        this.campaign = campaign;
        setName(this.campaign.getTitle());
    }

}
