package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class EpisodeNode extends AbstractStffNode {

    public EpisodeNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "E";
    }

}
