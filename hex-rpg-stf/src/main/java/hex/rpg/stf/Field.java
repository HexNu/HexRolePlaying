package hex.rpg.stf;

/**
 *
 * @author hln
 */
public interface Field {

    public static final String LABEL_SEPARATOR = ":";

    public enum Label {

        /**
         * Note lower case "x"!
         *
         * Field: id;
         */
        x("Id"),
        /**
         * Note lower case "p"!
         *
         * Field: parentId;
         */
        p("ParentId"),
        /**
         * Field: playerAlias;
         */
        A("PlayerAlias"),
        /**
         * Field: birthdate; (notedate for PlayerNote)
         */
        B("Date"),
        /**
         * Content.
         *
         * For episodes this is the text content.
         *
         * For supplements and character entities: If this is NOT text-content,
         * this should point to a file that is the content, ie image-file.
         *
         * Field: content; (text for PlayerNote)
         */
        C("Content"),
        /**
         * Field: description;
         */
        D("Description"),
        /**
         * Field: gender; [F, M, O, N/A] or [Female, Male, Other, Not
         * applicable]
         */
        G("Gender"),
        /**
         * Field: habitation;
         */
        H("Habitation"),
        /**
         * Field: refereeInfo;
         */
        I("RefereeInfo"),
        /**
         * Field: stats; (gaming stats for character et al)
         */
        J("GamingStats"),
        /**
         * Field: mediaType; Must be of image type for CharacterEntity portaits
         */
        M("MediaType"),
        /**
         * Field: refereeNotes;
         */
        N("RefereeNotes"),
        /**
         * Field: occupation;
         */
        O("Occupation"),
        /**
         * Field: portrait; Valid file path to portrait image file
         */
        P("Portrait"),
        /**
         * Field: species;
         */
        R("Species"),
        /**
         * Field: shortDescription;
         */
        S("ShortDescription"),
        /**
         * Field: title; (name for CharacterEntity, label for PlayerNote)
         */
        T("Title"),
        /**
         * Field: playerName
         */
        U("PlayerName"),
        /**
         * Field: index; (in parents that have sorted children)
         */
        X("IndexInParent"),
        /**
         * Field: type; Campaign.Type or Supplement.Type
         *
         */
        Y("Type");
        private final String description;

        private Label(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public String tag() {
            return name() + ":";
        }
        
        public static Label getByTag(String tag) {
            for (Label label : values()) {
                if (label.name().equals(tag.replaceAll(":", ""))) {
                    return label;
                }
            }
            return null;
        }

        /**
         * Line start marks new field.
         *
         * use with matches to check if line start marks beginning of a new
         * field.
         *
         * @return
         */
        public final static String regEx() {
            StringBuilder reBuilder = new StringBuilder("[");
            for (Label label : values()) {
                reBuilder.append(label.name());
            }
            return reBuilder.append("]{1}").append(LABEL_SEPARATOR).append(".*").toString();
        }
    }
}
