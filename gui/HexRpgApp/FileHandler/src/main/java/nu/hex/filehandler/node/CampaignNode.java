package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class CampaignNode extends AbstractStffNode {

    public CampaignNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "C";
    }

}
