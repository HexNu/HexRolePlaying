package hex.rpg.app.campaign.node;

import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author hln
 */
public class RpgRootNode extends AbstractNode {

    List<CampaignNode> campaignNodes = new ArrayList<>();
    public RpgRootNode() {
        super(Children.create(new RpgNodeChildFactory<>(), true));
    }

    public void addChild(CampaignNode campaignNode) {
        campaignNodes.add(campaignNode);
    }
    
    
}
