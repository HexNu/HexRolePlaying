package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterNode extends AbstractStffNode {

    public NonPlayingCharacterNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "N";
    }

}
