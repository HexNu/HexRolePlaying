package nu.hex.filehandler.node;

import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public class NonPlayingCharacterSupplementNode extends AbstractStffNode {

    public NonPlayingCharacterSupplementNode(TextSegment segment, Lookup lookup) {
        super(segment, lookup);
    }

    @Override
    protected String getIconName() {
        return "Ns";
    }

}
