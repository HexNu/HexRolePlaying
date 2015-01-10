package hex.rpg.xml;

import java.util.logging.Level;
import java.util.logging.Logger;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;
import se.digitman.util.Base64Coder;

/**
 *
 * @author hln
 */
public enum HexRpgNode {

    CAMPAIGN,
    CAMPAIGN_TYPE,
    CAMPAIGNS,
    CFX,
    CONTENT,
    DESCRIPTION,
    EPISODE,
    EPISODES,
    INFORMATION,
    REFEREE_INFO,
    REFEREE_NOTES,
    SHORT_DESCRIPTION,
    STORY,
    STORIES,
    SUPPLEMENT,
    SUPPLEMENTS,
    TITLE;

    public XmlNode getXmlNode() {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Node Type: {0}", name());
        return NodeFactory.createNode(name().toLowerCase().replace("_", "-"));
    }

    public XmlNode getXmlNode(String content) {
        XmlNode result = getXmlNode();
        if (content != null) {
            result.addText(content.replaceAll("\n", "\\\\\\\\"));
        }
        return result;
    }

    public XmlNode getXmlNode(byte[] content) {
        XmlNode result = getXmlNode();
        if (content != null) {
            result.addText(Base64Coder.encodeLines(content));
        }
        return result;
    }
}
