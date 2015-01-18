package hex.rpg.io.stf;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.io.AbstractZippedStream;
import hex.rpg.io.cft.CreateZippedTexStream;
import hex.rpg.stf.out.STFDocument;
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

/**
 *
 * @author hln
 */
public class CreateZippedStfStream extends AbstractZippedStream {
    private final STFDocument doc;

    public CreateZippedStfStream(Campaign campaign) {
        super(campaign);
        this.doc = new STFDocument();
        doc.createCampaign(campaign);
    }

    @Override
    public InputStream execute() {
        try {
            File resultFile = new File("/tmp/result.stf");
            FileOutputStream fileOut = new FileOutputStream(resultFile);
            ZipOutputStream out = new ZipOutputStream(fileOut, Charset.forName(Constants.UTF_8));
            ZipEntry texEntry = new ZipEntry(campaign.getTitle().replaceAll(" ", "_") + ".stff");
            out.putNextEntry(texEntry);
            out.write(doc.getContent().getBytes(Constants.UTF_8));
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
