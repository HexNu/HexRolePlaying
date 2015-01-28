package hex.rpg.api.modulesuport.gui;

import hex.rpg.api.modulesuport.ResourceProvider;
import java.io.InputStream;

/**
 *
 * @author thomas
 */
public enum IconType {

    ICONS(16),
    ICONS24(24);
    private final int size;

    private IconType(int size) {
        this.size = size;
    }

    public InputStream getStream(String name) {
        String path = name + getSizeAppendix() + ".png";
        return ResourceProvider.ICONS.getResourceStream(path);
    }

    private String getSizeAppendix() {
        return (size == 0 || size == 16 ? "" : size + "");
    }
}
