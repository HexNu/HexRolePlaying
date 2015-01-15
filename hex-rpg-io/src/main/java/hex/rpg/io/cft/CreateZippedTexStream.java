package hex.rpg.io.cft;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.AbstractZippedStream;
import hex.rpg.io.ResourceProvider;
import hex.rpg.xml.pack.node.RootNode;
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
import se.digitman.lightxml.transform.XmlTransformer;

/**
 *
 * @author hln
 */
public class CreateZippedTexStream extends AbstractZippedStream {

    private final RootNode rootNode;

    public CreateZippedTexStream(Campaign campaign) {
        super(campaign);
        rootNode = new RootNode(true);
        rootNode.addCampaign(campaign);
    }

    @Override
    public InputStream execute() {
        String texString = new XmlTransformer(rootNode.getXmlNode(), ResourceProvider.CAMPAIGN_BOOK_XSL.getResourceAsStream()).getResultAsString();
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
            addPortraitsToZip(out);
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
}
