package hex.rpg.core.domain.character.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "NonPlayingCharacter")
public class RpgNonPlayingCharacter implements NonPlayingCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(length = 8 * Constants.KB)
    private String refereeInfo;
    @Column(length = 8 * Constants.KB)
    private String refereeNotes;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private String species;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column
    private String occupation;
    @Column(length = 1 * Constants.KB)
    private String shortDescription;
    @Lob
    @Column(length = 64 * Constants.MB)
    private byte[] portrait;
    @Column
    private String mediaType;
    @Column(length = 8 * Constants.KB)
    private String description;
    @ManyToOne(targetEntity = RpgCampaign.class)
    private Campaign campaign;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "character", targetEntity = RpgNonPlayingCharacterSupplement.class)
    private final Set<Supplement> supplements = new HashSet<>();
    @Column
    private String habitation;
    @Column
    private String stats;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        this.refereeInfo = refereeInfo;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        this.refereeNotes = refereeNotes;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String getSpecies() {
        return species;
    }

    @Override
    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getOccupation() {
        return occupation;
    }

    @Override
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String getHabitation() {
        return habitation;
    }

    @Override
    public void setHabitation(String habitation) {
        this.habitation = habitation;
    }

    @Override
    public String getGamingStats() {
        return stats;
    }

    @Override
    public void setGamingStats(String stats) {
        this.stats = stats;
    }

    @Override
    public InputStream getPortrait() {
        return new ByteArrayInputStream(portrait);
    }

    @Override
    public byte[] getPortraitAsByteArray() {
        return portrait;
    }

    @Override
    public String getPortraitMediaType() {
        return mediaType;
    }

    @Override
    public void setPortraitMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String createPortraitFilePath() {
        return "Portrait/" + name.replaceAll(" ", "_") + "." + mediaType.substring(mediaType.lastIndexOf("/") + 1);
    }

    @Override
    public void setPortrait(byte[] portrait) {
        this.portrait = portrait;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<Supplement> getSupplements() {
        List<Supplement> result = new ArrayList<>(supplements);
        Collections.sort(result);
        return result;
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public boolean hasSupplements() {
        return !supplements.isEmpty();
    }

    @Override
    public int compareTo(CharacterEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
