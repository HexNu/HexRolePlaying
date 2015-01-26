package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class CampaignSupplementNode extends AbstractStffNode {

    public CampaignSupplementNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "Cs";
    }

}
