package hex.rpg.dto.deserializer;

import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.CreateSupplementDTO;

/**
 *
 * @author hln
 */
public abstract class AbstractSupplementDeserializer<T> {

    private final CreateSupplementDTO supplementDTO;
    private final Supplement supplement;
    
    // TODO: Hantering av content.

    public AbstractSupplementDeserializer(CreateSupplementDTO dto, Supplement supplement) {
        this.supplementDTO = dto;
        this.supplement = supplement;
    }

    protected Supplement parse() {
        supplement.setId(supplementDTO.id);
        supplement.setTitle(supplementDTO.title);
        supplement.setShortDescription(supplementDTO.shortDescription);
        supplement.setDescription(supplementDTO.description);
        supplement.setRefereeInfo(supplementDTO.refereeInfo);
        supplement.setRefereeNotes(supplementDTO.refereeNotes);
        supplement.setMediaType(supplementDTO.mediaType);
        supplement.setContent(supplementDTO.content);
        supplement.setIndex(supplementDTO.index);
        supplement.setType(Supplement.Type.getByString(supplementDTO.type));
        return supplement;
    }
    
    public T createSupplement() {
        return (T) parse();
    }
}
