package hex.rpg.dto.deserializer;

import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.dto.in.CreateEntityDTO;

/**
 *
 * @author hln
 */
public abstract class AbstractEntityDeserializer<E, D> {

    protected final CreateEntityDTO dto;
    private final NarrativeEntity entity;

    public AbstractEntityDeserializer(CreateEntityDTO dto, NarrativeEntity entity) {
        this.dto = dto;
        this.entity = entity;
        parse();
    }

    private void parse() {
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setShortDescription(dto.getShortDescription());
        entity.setDescription(dto.getDescription());
        entity.setRefereeInfo(dto.getRefereeInfo());
        entity.setRefereeNotes(dto.getRefereeNotes());
    }
    
    protected E getEntity() {
        return (E) entity;
    }

    protected D getDto() {
        return (D) dto;
    }

    public abstract E createEntity();
}
