package hex.rpg.app.domain.character;

import hex.rpg.app.domain.AppDomainEntity;
import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hln
 */
public class AppNonPlayingCharacter implements NonPlayingCharacter, AppDomainEntity {

    @Override
    public Campaign getCampaign() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setCampaign(Campaign campaign) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getRefereeInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getRefereeNotes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Gender getGender() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setGender(Gender gender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSpecies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSpecies(String species) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getBirthdate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBirthdate(Date birthdate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getOccupation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOccupation(String occupation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getHabitation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHabitation(String habitation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getGamingStats() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setGamingStats(String stats) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InputStream getPortrait() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] getPortraitAsByteArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPortrait(byte[] content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getPortraitMediaType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPortraitMediaType(String mediaType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String createPortraitFilePath() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getShortDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setShortDescription(String shortDescription) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Supplement> getSupplements() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addSupplement(Supplement supplement) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasSupplements() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getParentId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int compareTo(CharacterEntity o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
