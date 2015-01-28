package hex.rpg.app.campaign.node;

import hex.rpg.app.campaign.action.CreateCampaignAction;
import hex.rpg.core.domain.campaign.Campaign;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

/**
 *
 * @author hln
 */
public class RpgRootNode extends AbstractNode {

    List<Campaign> campaigns = new ArrayList<>();
    List<Action> actions = new ArrayList<>();

    public RpgRootNode(List<Campaign> campaigns) {
        super(Children.create(new RootNodeChildFactory(campaigns), true));
        actions.add(new CreateCampaignAction());
    }

    @Override
    public Action[] getActions(boolean context) {
        return actions.toArray(new Action[actions.size()]);
    }

}
