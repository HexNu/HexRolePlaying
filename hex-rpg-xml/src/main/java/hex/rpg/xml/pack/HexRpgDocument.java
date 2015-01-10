package hex.rpg.xml.pack;

import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class HexRpgDocument {

    private final XmlDocument result;

    public HexRpgDocument(XmlNode node) {
        this.result = new XmlDocument(node);
    }

    public String getAsString() {
        return result.toString();
    }

    public XmlDocument get() {
        return result;
    }

}
