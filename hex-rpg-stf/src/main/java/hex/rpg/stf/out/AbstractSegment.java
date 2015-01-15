package hex.rpg.stf.out;

import hex.rpg.stf.Segment;
import hex.rpg.stf.out.segment.DocumentBuilder;
import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.DomainEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.stf.Field;
import hex.rpg.stf.out.segment.SupplementSection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 * @param <T>
 */
public abstract class AbstractSegment<T extends DomainEntity> implements Segment {

    protected final T entity;
    protected final DocumentBuilder builder;

    public AbstractSegment(T entity, DocumentBuilder builder) {
        this.entity = entity;
        this.builder = builder;
        createGenericSegment();
    }

    public AbstractSegment(T entity) {
        this.entity = entity;
        builder = new DocumentBuilder();
        createGenericSegment();
    }

    private void createGenericSegment() {
        builder.append(entity.getClass());
        if (entity.getId() != null) {
            builder.append(Field.Label.x).append(entity.getId());
        }
        if (entity.getParentId() != null) {
            builder.append(Field.Label.p).append(entity.getParentId());
        }
        if (entity instanceof Supplement) {
            Supplement e = (Supplement) entity;
            builder.append(Field.Label.T).append(e.getTitle());
            builder.append(Field.Label.P).append(e.createPath());
            builder.append(Field.Label.S).append(e.getShortDescription());
            builder.append(Field.Label.D).append(e.getDescription());
            builder.append(Field.Label.I).append(e.getRefereeInfo());
            builder.append(Field.Label.N).append(e.getRefereeNotes());
            builder.append(Field.Label.M).append(e.getMediaType());
            builder.append(Field.Label.Z).append(e.getSize());
            builder.append(Field.Label.Y).append(e.getType().name());

        } else if (entity instanceof NarrativeEntity) {
            NarrativeEntity e = (NarrativeEntity) entity;
            builder.append(Field.Label.T).append(e.getTitle());
            builder.append(Field.Label.S).append(e.getShortDescription());
            builder.append(Field.Label.D).append(e.getDescription());
            builder.append(Field.Label.I).append(e.getRefereeInfo());
            builder.append(Field.Label.N).append(e.getRefereeNotes());
            if (e.hasSupplements()) {
                for (Supplement s : e.getSupplements()) {
                    createSupplementSections(s);
                }
            }
        } else if (entity instanceof CharacterEntity) {
            CharacterEntity e = (CharacterEntity) entity;
            builder.append(Field.Label.T).append(e.getName());
            builder.append(Field.Label.S).append(e.getShortDescription());
            builder.append(Field.Label.D).append(e.getDescription());
            builder.append(Field.Label.B).append(e.getBirthdate());
            builder.append(Field.Label.J).append(e.getGamingStats());
            builder.append(Field.Label.G).append(e.getGender().getLabel());
            builder.append(Field.Label.D).append(e.getHabitation());
            builder.append(Field.Label.O).append(e.getOccupation());
            builder.append(Field.Label.R).append(e.getSpecies());
            builder.append(Field.Label.P).append(e.createPortraitFilePath());
            builder.append(Field.Label.M).append(e.getPortraitMediaType());
        }
    }

    protected void createSupplementSections(NarrativeEntity e) {
        if (e.hasSupplements()) {
            List<Supplement> supplements = new ArrayList<>(e.getSupplements());
            Collections.sort(supplements);
            for (Supplement s : supplements) {
                new SupplementSection(s, builder).build();
            }
        }
    }

    protected void createSupplementSections(CharacterEntity e) {
        if (e.hasSupplements()) {
            List<Supplement> supplements = new ArrayList<>(e.getSupplements());
            Collections.sort(supplements);
            for (Supplement s : supplements) {
                new SupplementSection(s, builder).build();
            }
        }
    }

    protected void closeSegment() {
        builder.append(SEGMENT_DELIMITER).append("\n\n");
    }

    protected abstract void build();
}
