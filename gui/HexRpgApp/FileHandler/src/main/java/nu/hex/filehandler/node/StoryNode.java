package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class StoryNode extends AbstractStffNode {

    public StoryNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "S";
    }

}
