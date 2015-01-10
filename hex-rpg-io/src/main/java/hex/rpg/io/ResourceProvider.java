package hex.rpg.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author hln
 */
public enum ResourceProvider {

    CAMPAIGN_BOOK_XSL("xsl/campaign-book.xsl"),
    CALL_OF_CTHULHU_LOGO("xsl/images/Call_of_Cthulhu_Logo.png");
    private final String path;

    private ResourceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static ResourceProvider getLogoByName(String name) {
        for (ResourceProvider n : values()) {
            if (n.name().equalsIgnoreCase(name.replaceAll(" ", "_") + "_LOGO")) {
                return n;
            }
        }
        return null;
    }

    public byte[] getResourceAsByteArray() {
        return getBytesFromInputStream(getClass().getClassLoader().getResourceAsStream(path));
    }

    public StreamSource getResourceAsStream() {
        return new StreamSource(getClass().getClassLoader().getResourceAsStream(path));
    }

    private static byte[] getBytesFromInputStream(InputStream is) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();) {
            byte[] buffer = new byte[0xFFFF];

            for (int len; (len = is.read(buffer)) != -1;) {
                os.write(buffer, 0, len);
            }

            os.flush();

            return os.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
