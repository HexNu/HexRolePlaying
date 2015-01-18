package hex.rpg.stf;

/**
 *
 * @author hln
 */
public interface Parsing {

    public static final String REGEX_BASEPATH = "^[%]{2}\\s{0,}basepath\\s*.*",
            REGEX_SPLIT_BASEPATH_LINE = "basepath\\s*",
            REGEX_PROCESSING_INSTRUCTION = "^[%]{2}.*";

    public enum Instruction {

        BASEPATH;
        
        public String splitRegex() {
            return name().toLowerCase() + "\\s*";
        }
        
        public String matchRegex() {
            return "^[%]{2}\\s{1,}" + name().toLowerCase() + "\\s*.*";
        }
    }
}
