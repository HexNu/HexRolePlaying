package hex.rpg.core.domain.campaign;

import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.story.Story;
import java.util.List;

/**
 *
 * @author hln
 */
public interface Campaign extends NarrativeEntity {

    List<Story> getStories();

    void addStory(int index, Story story);

    void addStory(Story story);

    Type getType();

    void setType(Type type);

    public enum Type {

        COC("Call of Cthulhu 1920", "Call of Cthulhu"),
        COC_GASLIGHT("Cthulhu by Gaslight", "Call of Cthulhu"),
        COC_NOW("Cthulhu Now", "Call of Cthulhu");
        private final String label;
        private final String superType;

        private Type(String label, String superType) {
            this.label = label;
            this.superType = superType;
        }

        public String getLabel() {
            return label;
        }

        public String getSuperType() {
            return superType;
        }
    }
}
