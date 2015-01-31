package hex.rpg.dto.out;

import hex.rpg.core.domain.CharacterEntity.Gender;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.dto.AbstractDTO;
import hex.rpg.dto.LinkDTOBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hln
 */
public class FullNonPlayingCharacterDTO extends AbstractDTO {

    private final Long id;
    private final String name;
    private final String shortDescription;
    private final String description;
    private final String refereeInfo;
    private final String refereeNotes;
    private final Date birthdate;
    private final Gender gender;
    private final String occupation;
    private final String habitation;
    private final String species;
    private final String stats;
    private final String mediaType;
    private final byte[] portrait;
    private final List<FullSupplementDTO> supplements = new ArrayList<>();

    public FullNonPlayingCharacterDTO(NonPlayingCharacter character, LinkDTOBuilder linkBuilder) {
        if (linkBuilder != null) {
            addLink(linkBuilder.createPortraitDownloadLink(character));
        }
        id = character.getId();
        name = character.getName();
        shortDescription = character.getShortDescription();
        description = character.getDescription();
        refereeInfo = character.getRefereeInfo();
        refereeNotes = character.getRefereeNotes();
        birthdate = character.getBirthdate();
        gender = character.getGender();
        occupation = character.getOccupation();
        habitation = character.getHabitation();
        species = character.getSpecies();
        stats = character.getGamingStats();
        mediaType = character.getPortraitMediaType();
        portrait = character.getPortraitAsByteArray();
        character.getSupplements().stream().forEach((supplement) -> {
            supplements.add(new FullSupplementDTO(supplement, linkBuilder));
        });
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getRefereeInfo() {
        return refereeInfo;
    }

    public String getRefereeNotes() {
        return refereeNotes;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getHabitation() {
        return habitation;
    }

    public String getSpecies() {
        return species;
    }

    public String getStats() {
        return stats;
    }

    public String getMediaType() {
        return mediaType;
    }

    public byte[] getPortrait() {
        return portrait;
    }

    public List<FullSupplementDTO> getSupplements() {
        return supplements;
    }

}
