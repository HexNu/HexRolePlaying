package hex.rpg.io.cfx;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.NarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.story.Story;
import hex.rpg.xml.pack.nodes.RootNode;
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
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.XmlNode;

/**
 *
 * @author hln
 */
public class CreateCfxStream {

    private final XmlDocument result;
    private final List<Campaign> campaigns;
    private final Set<Supplement> supplements = new HashSet<>();

    public CreateCfxStream(List<Campaign> campaigns) {
        this.campaigns = campaigns;
        XmlNode node = new RootNode(campaigns).getXmlNode();
        result = new XmlDocument(node);
    }

    public InputStream execute() {
        campaigns.stream().forEach((entity) -> {
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
            out.write(result.toString().getBytes(Constants.UTF_8));
            out.closeEntry();
            out.flush();
            addSupplementsToZip(out);
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

    private void addSupplementsToZip(ZipOutputStream out) {
        supplements.stream().forEach((s) -> {
            try {
                ZipEntry zipEntry = new ZipEntry(s.createPath());
                out.putNextEntry(zipEntry);
                out.write(s.getContentAsByteArray());
                out.closeEntry();
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(CreateCfxStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void collectSupplements(NarrativeEntity entity) {
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
}
