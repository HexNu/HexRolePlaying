package hex.rpg.app.campaign.node;

import hex.rpg.app.domain.campaign.AppCampaign;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author hln
 */
public class RpgRootNode extends AbstractNode {

    List<Campaign> campaigns = new ArrayList<>();

    public RpgRootNode(List<Campaign> campaigns) {
        super(Children.create(new RootNodeChildFactory(campaigns), true));
    }
}
