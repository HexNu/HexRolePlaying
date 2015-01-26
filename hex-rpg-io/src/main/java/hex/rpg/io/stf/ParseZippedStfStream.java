package hex.rpg.io.stf;

import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.stf.in.STFDocumentParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author hln
 */
public class ParseZippedStfStream {

    private static final String TEMP_PATH = "/tmp/stf/";
    private final ZipInputStream stream;
    private final File outputDirectory;

    public ParseZippedStfStream(InputStream inputStream) {
        this(inputStream, TEMP_PATH);
    }

    public ParseZippedStfStream(InputStream inputStream, String path) {
        this.stream = new ZipInputStream(inputStream);
        outputDirectory = new File(path);
        outputDirectory.mkdirs();
    }

    public Campaign parse() {
        try {
            String stffFilePath = null;
            String fullPath = null;

            byte[] buf = new byte[1024];
            ZipEntry zipentry = stream.getNextEntry();
            while (zipentry != null) {
                String entryName = zipentry.getName();
                if (entryName.endsWith(".stff")) {
                    stffFilePath = outputDirectory + File.separator + entryName;
                }
                int len;
                FileOutputStream out;
                File newFile = new File(entryName);
                String directory = newFile.getParent();
                if (directory != null) {
                    fullPath = outputDirectory + File.separator + newFile;
                    if (!fullPath.endsWith(File.separator)) {
                        fullPath = fullPath.substring(0, fullPath.lastIndexOf(File.separator) + 1);
                    }
                    if (!new File(fullPath).exists()) {
                        new File(fullPath).mkdirs();
                    }
                }

                out = new FileOutputStream(outputDirectory + File.separator + entryName);
                while ((len = stream.read(buf, 0, 1024)) > -1) {
                    out.write(buf, 0, len);
                }
                out.close();
                stream.closeEntry();
                zipentry = stream.getNextEntry();
            }
            stream.close();
            if (stffFilePath != null) {
                STFDocumentParser documentParser = new STFDocumentParser(new FileInputStream(stffFilePath));
                return documentParser.parse();
            }
        } catch (IOException ex) {
            Logger.getLogger(ParseZippedStfStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
