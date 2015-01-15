package hex.rpg.io;

import hex.rpg.core.domain.CharacterEntity;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.core.domain.story.Story;
import hex.rpg.io.cft.CreateZippedTexStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author hln
 */
public abstract class AbstractZippedStream {

    protected final Campaign campaign;
    private final Set<Supplement> supplements = new HashSet<>();
    private final Set<PlayingCharacter> characters = new HashSet<>();

    public AbstractZippedStream(Campaign campaign) {
        this.campaign = campaign;
        collectSupplements(campaign);
    }

    protected abstract InputStream execute();

    public void addCharacter(PlayingCharacter character) {
        characters.add(character);
    }

    public void addCharacters(List<PlayingCharacter> characters) {
        this.characters.addAll(characters);
    }

    protected void addSupplementsToZip(ZipOutputStream out) {
        for (Supplement supplement : supplements) {
            addSupplementToZip(out, supplement);
        }
    }

    protected void collectSupplements(CharacterEntity entity) {
        entity.getSupplements().stream().forEach((supplement) -> {
            supplements.add(supplement);
        });
    }

    protected void collectSupplements(NarrativeEntity entity) {
        entity.getSupplements().stream().forEach((supplement) -> {
            supplements.add(supplement);
        });
        if (entity instanceof Campaign) {
            ((Campaign) entity).getStories().stream().forEach((story) -> {
                collectSupplements(story);
            });
        } else if (entity instanceof Story) {
            ((Story) entity).getEpisodes().stream().forEach((episode) -> {
                collectSupplements(episode);
            });
        }
    }

    private void addSupplementToZip(ZipOutputStream out, Supplement supplement) {
        try {
            ZipEntry zipEntry = new ZipEntry(supplement.createPath());
            out.putNextEntry(zipEntry);
            out.write(supplement.getContentAsByteArray());
            out.closeEntry();
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addLogoToZip(ZipOutputStream out) {
        try {
            byte[] resource = ResourceProvider.getLogoByName(campaign.getType().getLabel()).getResourceAsByteArray();
            if (resource != null) {
                ZipEntry zipEntry = new ZipEntry("Image/" + campaign.getType().getLabel().replaceAll(" ", "_") + "_Logo.png");
                out.putNextEntry(zipEntry);
                out.write(resource);
                out.closeEntry();
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void addPortraitsToZip(ZipOutputStream out) {
        for (NonPlayingCharacter character : campaign.getCharacters()) {
            byte[] portrait = character.getPortraitAsByteArray();
            if (portrait != null) {
                try {
                    ZipEntry zipEntry = new ZipEntry("Portrait/" + character.getName().replaceAll(" ", "_") + "."
                            + character.getPortraitMediaType().substring(character.getPortraitMediaType().lastIndexOf("/") + 1));
                    out.putNextEntry(zipEntry);
                    out.write(portrait);
                    out.closeEntry();
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
