package hex.rpg.api.modulesuport.file;

import java.io.File;
import java.util.Locale;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author hln
 */
public class ImageFileFilter extends FileFilter {

    private final String[] extensions = {
        "jpg", "jpeg", "jpe",
        "gif", "giff", "gfa",
        "png",
        "bmp", "dib", "rle", "2bp",
        "wbmp", "wbm", "wbp"
    };

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        for (String extension : extensions) {
            if (file.getName().toLowerCase(new Locale("SV")).endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Image Files (jpg, gif, png, bmp, wbmp)";
    }
}
