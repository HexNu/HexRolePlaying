package hex.rpg.core.domain;

import java.util.List;

/**
 *
 * @author hln
 */
public interface NarrativeEntity extends DomainEntity {

    String getTitle();

    void setTitle(String title);

    String getRefereeInfo();

    void setRefereeInfo(String refereeInfo);

    String getRefereeNotes();

    void setRefereeNotes(String refereeNotes);

    String getShortDescription();

    void setShortDescription(String shortDescription);

    String getDescription();

    void setDescription(String description);

    List<Supplement> getSupplements();

    void addSupplement(Supplement supplement);

    boolean hasSupplements();
}
