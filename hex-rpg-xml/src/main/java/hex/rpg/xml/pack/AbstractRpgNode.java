package hex.rpg.xml.pack;

import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.xml.HexRpgNode;
import hex.rpg.xml.TextParser;
import hex.rpg.xml.pack.node.SupplementNode;
import java.util.Set;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 * @param <T>
 */
public abstract class AbstractRpgNode<T extends DomainEntity> {

    private final T entity;
    private final boolean texFormated;
    private final Set<String> names;
    private final Set<String> places;
    private final Set<String> creatures;

    protected AbstractRpgNode(T entity, boolean texFormated, Set<String> names, Set<String> places, Set<String> creatures) {
        this.entity = entity;
        this.texFormated = texFormated;
        this.names = names;
        this.places = places;
        this.creatures = creatures;
    }

    protected boolean isTexFormated() {
        return texFormated;
    }

    protected NarrativeEntity narrativeEntity() {
        return entity instanceof NarrativeEntity ? (NarrativeEntity) entity : null;
    }

    protected CharacterEntity characterEntity() {
        return entity instanceof CharacterEntity ? (CharacterEntity) entity : null;
    }

    protected T genericEntity() {
        return entity;
    }

    public abstract XmlNode getXmlNode();

    protected XmlNode buildNode(HexRpgNode rpgNode) {
        XmlNode node = rpgNode.getXmlNode();
        if (entity != null) {
            if (entity instanceof NarrativeEntity) {
                String shortDescription = narrativeEntity().getShortDescription();
                String description = narrativeEntity().getDescription();
                String refereeInfo = narrativeEntity().getRefereeInfo();
                String refereeNotes = narrativeEntity().getRefereeNotes();
                if (texFormated) {
                    shortDescription = parseNode(shortDescription);
                    description = parseNode(description);
                    refereeInfo = parseNode(refereeInfo);
                    refereeNotes = parseNode(refereeNotes);
                }
                node.addChild(HexRpgNode.TITLE.getXmlNode(narrativeEntity().getTitle()));
                node.addChild(HexRpgNode.SHORT_DESCRIPTION.getXmlNode(shortDescription));
                node.addChild(HexRpgNode.DESCRIPTION.getXmlNode(description));
                node.addChild(HexRpgNode.REFEREE_INFO.getXmlNode(refereeInfo));
                node.addChild(HexRpgNode.REFEREE_NOTES.getXmlNode(refereeNotes));
            } else if (entity instanceof CharacterEntity) {
                node.addAttribute("portrait-media-type", characterEntity().getPortraitMediaType());
                node.addAttribute("portrait-file-path", characterEntity().createPortraitFilePath());
                node.addChild(HexRpgNode.NAME.getXmlNode(characterEntity().getName()));
                node.addChild(HexRpgNode.GENDER.getXmlNode(characterEntity().getGender().getLabel()));
                node.addChild(HexRpgNode.SPECIES.getXmlNode(characterEntity().getSpecies()));
                node.addChild(HexRpgNode.BIRTHDATE.getXmlNode(characterEntity().getBirthdate()));
                node.addChild(HexRpgNode.OCCUPATION.getXmlNode(characterEntity().getOccupation()));
                node.addChild(HexRpgNode.SHORT_DESCRIPTION.getXmlNode(characterEntity().getShortDescription()));
                node.addChild(HexRpgNode.DESCRIPTION.getXmlNode(characterEntity().getDescription()));
                node.addChild(HexRpgNode.HABITATION.getXmlNode(characterEntity().getHabitation()));
                node.addChild(HexRpgNode.GAMING_STATS.getXmlNode(characterEntity().getGamingStats()));
            }
        }
        handleSupplements(node);
        return node;

    }

    protected final String parseNode(String text) {
        TextParser parser = new TextParser(text);
        text = parser.parse(texFormated);
        getNames().addAll(parser.getNames(getNames()));
        getPlaces().addAll(parser.getPlaces(getPlaces()));
        getCreatures().addAll(parser.getPlaces(getCreatures()));
        return text;
    }

    private void handleSupplements(XmlNode node) {
        if (entity instanceof NarrativeEntity && narrativeEntity().hasSupplements()) {
            XmlNode supplementsNode = HexRpgNode.SUPPLEMENTS.getXmlNode();
            narrativeEntity().getSupplements().stream().forEach((supplement) -> {
                supplementsNode.addChild(new SupplementNode(supplement, texFormated, names, places, creatures).getXmlNode());
            });
            node.addChild(supplementsNode);
        } else if (entity instanceof CharacterEntity && characterEntity().hasSupplements()) {
            XmlNode supplementsNode = HexRpgNode.SUPPLEMENTS.getXmlNode();
            characterEntity().getSupplements().stream().forEach((supplement) -> {
                supplementsNode.addChild(new SupplementNode(supplement, texFormated, names, places, creatures).getXmlNode());
            });
            node.addChild(supplementsNode);
        }
    }

    protected Set<String> getNames() {
        return names;
    }

    protected Set<String> getPlaces() {
        return places;
    }

    protected Set<String> getCreatures() {
        return creatures;
    }
}
