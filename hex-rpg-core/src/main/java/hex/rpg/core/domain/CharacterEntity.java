package hex.rpg.core.domain;

import hex.rpg.core.domain.campaign.Campaign;
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

    String getShortDescription();

    void setShortDescription(String shortDescription);

    String getDescription();

    void setDescription(String description);

    List<Supplement> getSupplements();

    void addSupplement(Supplement supplement);

    boolean hasSupplements();

    public enum Gender {

        FEMALE, MALE, OTHER, NA;
    }
}
