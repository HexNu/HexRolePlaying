package hex.rpg.core.domain;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hln
 */
public interface CharacterEntity extends DomainEntity, Comparable<CharacterEntity> {

    void setName(String name);

    Gender getGender();

    void setGender(Gender gender);

    String getSpecies();

    void setSpecies(String species);

    Date getBirthdate();

    void setBirthdate(Date birthdate);

    String getOccupation();

    void setOccupation(String occupation);

    String getHabitation();

    void setHabitation(String habitation);

    String getGamingStats();

    void setGamingStats(String stats);

    InputStream getPortrait();

    byte[] getPortraitAsByteArray();

    void setPortrait(byte[] content);

    String getPortraitMediaType();

    void setPortraitMediaType(String mediaType);

    String createPortraitFilePath();

    String getShortDescription();

    void setShortDescription(String shortDescription);

    String getDescription();

    void setDescription(String description);

    List<Supplement> getSupplements();

    void addSupplement(Supplement supplement);

    boolean hasSupplements();

    public enum Gender {

        FEMALE("F", "Female"),
        MALE("M", "Male"),
        OTHER("O", "Other"),
        NA("N/A", "Not applicable");
        private final String code;
        private final String label;

        private Gender(String code, String label) {
            this.code = code;
            this.label = label;
        }

        public String getCode() {
            return code;
        }

        public String getLabel() {
            return label;
        }

        public static Gender getByString(String string) {
            try {
                valueOf(string);
            } catch (IllegalArgumentException e) {
                for (Gender g : values()) {
                    if (g.getCode().equalsIgnoreCase(string) || g.getLabel().equalsIgnoreCase(string)) {
                        return g;
                    }
                }
            }
            return null;
        }
    }
}
