package hex.rpg.io.cft;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.ResourceProvider;
import hex.rpg.xml.pack.HexRpgDocument;
import hex.rpg.xml.pack.nodes.RootNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import se.digitman.lightxml.XmlDocument;
import se.digitman.lightxml.transform.XmlTransformer;

/**
 *
 * @author hln
 */
public class CreateZippedTexStream {

    private final XmlDocument xmlDoc;
    private final Campaign campaign;

    public CreateZippedTexStream(Campaign campaign) {
        this.campaign = campaign;
        this.xmlDoc = new HexRpgDocument(new RootNode(campaign).getXmlNode()).get();

    }

    public InputStream execute() {
        String texString = new XmlTransformer(xmlDoc, ResourceProvider.CAMPAIGN_BOOK_XSL.getResourceAsStream()).getResultAsString();
        try {
            File resultFile = new File("/tmp/result.cft");
            FileOutputStream fileOut = new FileOutputStream(resultFile);
            ZipOutputStream out = new ZipOutputStream(fileOut, Charset.forName(Constants.UTF_8));
            ZipEntry texEntry = new ZipEntry(campaign.getTitle().replaceAll(" ", "_") + ".tex");
            out.putNextEntry(texEntry);
            out.write(texString.getBytes(Constants.UTF_8));
            out.closeEntry();
            out.flush();
            addLogoToZip(out);
            addSupplementsToZip(out);
            out.flush();
            out.close();
            return new FileInputStream(resultFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void addSupplementsToZip(ZipOutputStream out) {
        campaign.getSupplements().stream().forEach((supplement) -> {
            addSupplementToZip(out, supplement);
        });
        campaign.getStories().stream().map((story) -> {
            story.getSupplements().stream().forEach((supplement) -> {
                addSupplementToZip(out, supplement);
            });
            return story;
        }).forEach((story) -> {
            story.getEpisodes().stream().forEach((episode) -> {
                episode.getSupplements().stream().forEach((supplement) -> {
                    addSupplementToZip(out, supplement);
                });
            });
        });
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

    private void addLogoToZip(ZipOutputStream out) {
        try {
            byte[] resource = ResourceProvider.getLogoByName(campaign.getType().getLabel()).getResourceAsByteArray();
            if (resource != null) {
                ZipEntry zipEntry = new ZipEntry("Images/" + campaign.getType().getLabel().replaceAll(" ", "_") + "_Logo.png");
                out.putNextEntry(zipEntry);
                out.write(resource);
                out.closeEntry();
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateZippedTexStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
