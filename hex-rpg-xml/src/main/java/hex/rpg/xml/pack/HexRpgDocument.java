package hex.rpg.xml.pack;

import hex.rpg.xml.pack.node.RootNode;
import java.util.Set;
import se.digitman.lightxml.XmlDocument;

/**
 *
 * @author hln
 */
public class HexRpgDocument {

    private final XmlDocument result;
    private final RootNode rootNode;

    public HexRpgDocument(RootNode rootNode) {
        this.rootNode = rootNode;
        this.result = new XmlDocument(this.rootNode.getXmlNode());
    }

    public String getAsString() {
        return result.toString();
    }

    public XmlDocument get() {
        return result;
    }

    public Set<String> getNames() {
        return rootNode.getNames();
    }

    public Set<String> getPlaces() {
        return rootNode.getPlaces();
    }

    public Set<String> getCreatures() {
        return rootNode.getCreatures();
    }

}
