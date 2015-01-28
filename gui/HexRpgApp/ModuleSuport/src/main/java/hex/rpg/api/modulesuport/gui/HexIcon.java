package hex.rpg.api.modulesuport.gui;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author thomas
 */
public class HexIcon extends ImageIcon {

    private static final IconType DEFAULT_ICON_TYPE = IconType.ICONS;

    public HexIcon(String name) {
        this(name, DEFAULT_ICON_TYPE);
    }

    public HexIcon(String name, String comment) {
        this(name, comment, DEFAULT_ICON_TYPE);
    }

    public HexIcon(String name, IconType type) {
        this(name, null, type);
    }

    public HexIcon(String name, String comment, IconType type) {
        super(getImage(name, type), comment);
    }

    public static Image getImage(String name) {
        return getImage(name, DEFAULT_ICON_TYPE);
    }

    public static Image getImage(String name, String bullet) {
        Image result = getImage(name);
        result.getGraphics().drawImage(getImage("bullet_" + bullet), 0, 0, null);
        return result;
    }

    public static Image getImage(String name, IconType type) {
        InputStream imageStream = type.getStream(name);
        if (imageStream != null) {
            try {
                return ImageIO.read(imageStream);
            } catch (IOException ex) {
                try {
                    return ImageIO.read(type.getStream("warning"));
                } catch (IOException ex1) {
                    throw new RuntimeException(ex1);
                }
            }
        } else {
            try {
                return ImageIO.read(type.getStream("warning"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

//    public static Image getApplicationImage() {
//        return getImageByClasspath("/ble/support/gui/graphics/venus.png");
//    }

    public static Image getCommonImage(String name) {
        return getImageByClasspath("images/" + name + ".png");
    }

    private static Image getImageByClasspath(String classpath) {
        try {
            return ImageIO.read(HexIcon.class.getResourceAsStream(classpath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Icon getIcon(String name) {
        return new HexIcon(name);
    }
}
