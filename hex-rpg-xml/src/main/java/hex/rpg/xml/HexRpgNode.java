package hex.rpg.xml;

import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public enum HexRpgNode {

    CAMPAIGN,
    CAMPAIGN_TYPE,
    CAMPAIGNS,
    CFF,
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
        return NodeFactory.createNode(name().toLowerCase().replace("_", "-"));
    }

    public XmlNode getXmlNode(String content) {
        XmlNode result = getXmlNode();
        result.addText(content);
        return result;
    }

}
