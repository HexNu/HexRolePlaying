package hex.rpg.stf.out.segment;

import hex.rpg.core.domain.Supplement;
import hex.rpg.stf.out.AbstractSegment;

/**
 *
 * @author hln
 */
public class SupplementSection extends AbstractSegment<Supplement> {

    public SupplementSection(Supplement entity, DocumentBuilder builder) {
        super(entity, builder);
    }

    @Override
    public void build() {
        closeSegment();
    }
}
