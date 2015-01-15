package hex.rpg.xml;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.digitman.lightxml.NodeFactory;
import se.digitman.lightxml.XmlNode;
import se.digitman.util.Base64Coder;
import se.digitman.util.DateUtilities;

/**
 *
 * @author hln
 */
public enum HexRpgNode {

    BIRTHDATE,
    CAMPAIGN,
    CAMPAIGN_TYPE,
    CAMPAIGNS,
    CFX,
    CONTENT,
    DATE,
    DESCRIPTION,
    EPISODE,
    EPISODES,
    GAMING_STATS,
    GENDER,
    HABITATION,
    INFORMATION,
    LABEL,
    NAME,
    NON_PLAYING_CHARACTER,
    NON_PLAYING_CHARACTERS,
    OCCUPATION,
    PLAYER_ALIAS,
    PLAYER_NAME,
    PLAYER_NOTE,
    PLAYER_NOTES,
    PLAYING_CHARACTER,
    PLAYING_CHARACTERS,
    PORTRAIT,
    REFEREE_INFO,
    REFEREE_NOTES,
    SHORT_DESCRIPTION,
    SPECIES,
    STORY,
    STORIES,
    SUPPLEMENT,
    SUPPLEMENTS,
    TEXT,
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

    public XmlNode getXmlNode(Date content) {
        XmlNode result = getXmlNode();
        if (content != null) {
            result.addText(new DateUtilities(content).toString());
        }
        return result;
    }
}
