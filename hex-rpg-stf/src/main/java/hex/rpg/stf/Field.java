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
        x,
        /**
         * Note lower case "p"!
         *
         * Field: parentId;
         */
        p,
        /**
         * Field: playerAlias;
         */
        A,
        /**
         * Field: birthdate; (notedate for PlayerNote)
         */
        B,
        /**
         * Content.
         *
         * For episodes this is the text content.
         *
         * For supplements and character entities: If this is NOT text-content,
         * this should point to a file that is the conent, ie image-file.
         *
         * Field: content; (text for PlayerNote)
         */
        C,
        /**
         * Field: description;
         */
        D,
        /**
         * Field: gender; [F, M, O, N/A] or [Female, Male, Other, Not
         * applicable]
         */
        G,
        /**
         * Field: habitation;
         */
        H,
        /**
         * Field: refereeInfo;
         */
        I,
        /**
         * Field: stats; (gaming stats for character et al)
         */
        J,
        /**
         * Field: mediaType; Must be of image type for CharacterEntity portaits
         */
        M,
        /**
         * Field: refereeNotes;
         */
        N,
        /**
         * Field: occupation;
         */
        O,
        /**
         * Field: portrait; Valid file path to portrait image file
         */
        P,
        /**
         * Field: species;
         */
        R,
        /**
         * Field: shortDescription;
         */
        S,
        /**
         * Field: title; (name for CharacterEntity, label for PlayerNote)
         */
        T,
        /**
         * Field: playerName
         */
        U,
        /**
         * Field: index; (in parents that have sorted children)
         */
        X,
        /**
         * Field: type; Campaign.Type or Supplement.Type
         *
         */
        Y,
        /**
         * Field: size;
         */
        Z;

        public String tag() {
            return name() + ":";
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
