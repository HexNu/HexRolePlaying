package hex.rpg.api.modulesuport.gui;

import java.io.InputStream;

/**
 *
 * @author thomas
 */
public enum IconType {

    IMAGES("fatcow", 16),
    IMAGES24("fatcow", 24),
    System("graphics", 0);
    private final String dirName;
    private final int size;
    private static final String BASE_PATH = IconType.class.getPackage().getName().replace('.', '/') + "/";

    private IconType(String dirName, int size) {
        this.dirName = dirName;
        this.size = size;
    }

    public InputStream getStream(String name) {
        String path = BASE_PATH + dirName + "/" + name + getSizeAppendix() + ".png";
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    private String getSizeAppendix() {
        return (size == 0 || size == 16 ? "" : size + "");
    }
}
