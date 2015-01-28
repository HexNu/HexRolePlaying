package hex.rpg.app.client.assembler;

import hex.rpg.core.domain.Supplement;
import hex.rpg.dto.in.SupplementDTO;

/**
 *
 * @author hln
 */
public abstract class AbstractSupplementAssembler<T> {

    private final SupplementDTO supplementDTO;
    private final Supplement supplement;
    
    // TODO: Hantering av content.

    public AbstractSupplementAssembler(SupplementDTO dto, Supplement supplement) {
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
        supplement.setIndex(supplementDTO.index);
        supplement.setType(Supplement.Type.getByString(supplementDTO.type));
        return supplement;
    }
    
    public T createSupplement() {
        return (T) parse();
    }
}
