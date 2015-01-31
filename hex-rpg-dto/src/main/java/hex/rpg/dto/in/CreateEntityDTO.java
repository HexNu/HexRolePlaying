package hex.rpg.dto.in;

/**
 *
 * @author hln
 */
public interface CreateEntityDTO {

    Long getId();

    String getTitle();

    String getShortDescription();

    String getDescription();

    String getRefereeInfo();

    String getRefereeNotes();
}
