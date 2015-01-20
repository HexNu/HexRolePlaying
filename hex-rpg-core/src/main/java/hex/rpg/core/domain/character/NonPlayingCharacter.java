package hex.rpg.core.domain.character;

import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.campaign.Campaign;

/**
 *
 * @author hln
 */
public interface NonPlayingCharacter extends CharacterEntity {

    Campaign getCampaign();

    void setCampaign(Campaign campaign);

    String getRefereeInfo();

    void setRefereeInfo(String refereeInfo);

    String getRefereeNotes();

    void setRefereeNotes(String refereeNotes);
}
