package hex.rpg.io.cfx;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.character.PlayingCharacter;
import hex.rpg.io.AbstractZippedStream;
import hex.rpg.xml.pack.HexRpgDocument;
import hex.rpg.xml.pack.node.RootNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
public class CreateCfxStream extends AbstractZippedStream {

    private final HexRpgDocument result;
    private final Set<Supplement> supplements = new HashSet<>();
    private final List<PlayingCharacter> playingCharacters;

    public CreateCfxStream(Campaign campaign, List<PlayingCharacter> playingCharacters) {
        super(campaign);
        this.playingCharacters = playingCharacters;
        RootNode rootNode = new RootNode();
        rootNode.addCampaign(campaign);
        result = new HexRpgDocument(rootNode);
    }

    @Override
    public InputStream execute() {
        collectSupplements(campaign);
        if (playingCharacters != null && !playingCharacters.isEmpty()) {
            playingCharacters.stream().forEach((entity) -> {
                collectSupplements(entity);
            });
        }
        campaign.getCharacters().stream().forEach((entity) -> {
            collectSupplements(entity);
        });
        return createZip();
    }

    private InputStream createZip() {
        try {
            File resultFile = new File("/tmp/result.cfx");
            FileOutputStream fileOut = new FileOutputStream(resultFile);
            ZipOutputStream out = new ZipOutputStream(fileOut);
            ZipEntry xmlEntry = new ZipEntry("content.xml");
            out.putNextEntry(xmlEntry);
            out.write(result.get().toString().getBytes(Constants.UTF_8));
            out.closeEntry();
            out.flush();
            addSupplementsToZip(out);
            addLogoToZip(out);
            addPortraitsToZip(out);
            out.flush();
            out.close();
            return new FileInputStream(resultFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateCfxStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateCfxStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
