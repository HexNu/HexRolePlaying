package hex.rpg.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hln
 */
public class TextParser {

//    private static final String NAME_BEGIN = "«",
//            NAME_END = "»",
//            PLACE_BEGIN = "‘",
//            PLACE_END = "’",
//            CREATURE_BEGIN = "“",
//            CREATURE_END = "”",
//            HIDDEN_PART_BEGIN = "(",
//            HIDDEN_PART_END = ")";
    private static final String COLLECTING_BEGIN = "«",
            COLLECTING_END = "»",
            NAME_TAG = "£",
            PLACE_TAG = "@",
            CREATURE_TAG = "¢",
            HIDDEN_PART_BEGIN = "(",
            HIDDEN_PART_END = ")";
    private final Set<String> names;
    private final Set<String> places;
    private final Set<String> creatures;

    private final String content;

    // ¢ = [AltGr + Shift + 5];
    // £ = [AltGr + 3]
    // @ = [AltGr + 2]
    // « = [AltGr + Shift + 8] eller [AltGr + Z]
    // » = [AltGr + Shift + 9] eller [AltGr + X]
    // “ = [AltGr + V]
    // ” = [AltGr + B]
    // ’ = [AltGr + Shift + B]
    // ‘ = [AltGr + Shift + V]
    public TextParser(String content) {
        this.content = content;
        names = new LinkedHashSet<>();
        places = new LinkedHashSet<>();
        creatures = new LinkedHashSet<>();
    }

    public Set<String> getNames(Set<String> nameSet) {
        nameSet.addAll(names);
        return nameSet;
    }

    public Set<String> getPlaces(Set<String> placeList) {
        placeList.addAll(names);
        return placeList;
    }

    public Set<String> getCreatures(Set<String> creatureList) {
        creatureList.addAll(creatureList);
        return creatureList;
    }

    public String parse() {
        return parse(false);
    }

    public String parse(boolean texFormated) {
        if (content == null) {
            return null;
        }
        StringBuilder resultBuilder = new StringBuilder();
        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder placeBuilder = new StringBuilder();
        StringBuilder creatureBuilder = new StringBuilder();
        StringReader reader = new StringReader(content);
        boolean collectingStarted = false;
        boolean nameStarted = false;
        boolean placeStarted = false;
        boolean creatureStarted = false;
        boolean hiddenPartStarted = false;
        for (int i = 0; i < content.length(); i++) {
            try {
                String s = Character.toString((char) reader.read());
                switch (s) {
                    case "\n":
                        if (texFormated) {
                            resultBuilder.append("\\\\");
                        } else {
                            resultBuilder.append("\n");
                        }
                        break;
                    case COLLECTING_BEGIN:
                        collectingStarted = true;
                        break;
                    case COLLECTING_END:
                        if (texFormated) {
                            resultBuilder.append("}");
                        }
                        if (nameStarted) {
                            nameStarted = false;
                            names.add(nameBuilder.toString().trim());
                        }
                        if (placeStarted) {
                            placeStarted = false;
                            places.add(nameBuilder.toString().trim());
                        }
                        if (creatureStarted) {
                            creatureStarted = false;
                            creatures.add(camelCaseString(creatureBuilder.toString().trim()));
                        }
                        break;
                    case NAME_TAG:
                        if (collectingStarted) {
                            nameStarted = true;
                            nameBuilder = new StringBuilder();
                            if (texFormated) {
                                resultBuilder.append("\\Person{");
                            }
                        }
                        break;
                    case PLACE_TAG:
                        if (collectingStarted) {
                            placeStarted = true;
                            placeBuilder = new StringBuilder();
                            if (texFormated) {
                                resultBuilder.append("\\Place{");
                            }
                        }
                        break;
                    case CREATURE_TAG:
                        if (collectingStarted) {
                            creatureStarted = true;
                            creatureBuilder = new StringBuilder();
                            if (texFormated) {
                                resultBuilder.append("\\Creature{");
                            }
                        }
                        break;
                    case HIDDEN_PART_BEGIN:
                        if (collectingStarted) {
                            hiddenPartStarted = true;
                        } else {
                            resultBuilder.append(s);
                        }
                        break;
                    case HIDDEN_PART_END:
                        if (hiddenPartStarted) {
                            hiddenPartStarted = false;
                        } else {
                            resultBuilder.append(s);
                        }
                        break;
                    default:
                        if (nameStarted && !(nameBuilder.toString().equals("") && s.equals(NAME_TAG))) {
                            nameBuilder.append(s);
                        }
                        if (placeStarted && !(placeBuilder.toString().equals("") && s.equals(PLACE_TAG))) {
                            placeBuilder.append(s);
                        }
                        if (creatureStarted && !(creatureBuilder.toString().equals("") && s.equals(CREATURE_TAG))) {
                            creatureBuilder.append(s);
                        }
                        if (!hiddenPartStarted) {
                            resultBuilder.append(s);
                        }
                }
            } catch (IOException ex) {
                Logger.getLogger(TextParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultBuilder.toString().replaceAll("  ", " ").replaceAll("\\{ ", "{").replaceAll("\n ", "\n").replaceAll("\\\\\\\\\n\\\\\\\\\n", "\n\n");
    }

    private String camelCaseString(String string) {
        String[] parts = string.split("[^\\p{L}]");
        for (String part : parts) {
            if (part != null && !part.isEmpty()) {
                String upperCasedPart = part.substring(0, 1).toUpperCase() + part.substring(1);
                string = string.replaceFirst(part, upperCasedPart);
            }
        }
        return string;
    }
}
