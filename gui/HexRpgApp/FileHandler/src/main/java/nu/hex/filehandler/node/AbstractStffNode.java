package nu.hex.filehandler.node;

import hex.rpg.stf.ContentType;
import hex.rpg.stf.Field;
import java.awt.Image;
import java.lang.reflect.InvocationTargetException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport.ReadOnly;
import org.openide.nodes.Sheet;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;

/**
 *
 * @author hln
 */
public abstract class AbstractStffNode extends AbstractNode {

    private final TextSegment segment;

    public AbstractStffNode(TextSegment segment, Lookup lookup) {
        super(isLeafNode(segment)
                ? Children.LEAF
                : Children.create(new StffNodeChildFactory(segment, lookup), true));
        this.segment = segment;
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();
        Sheet.Set set = Sheet.createPropertiesSet();
        sheet.put(set);
        set.put(new TypeProperty(this));
        segment.getFields().stream().forEach((field) -> {
            set.put(new TextFieldProperty(field));
        });
        return sheet;
    }

    @Override
    public String getName() {
        return segment.getName();
    }

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage("nu/hex/filehandler/node/" + getIconName() + ".png");
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    protected abstract String getIconName();

    private static boolean isLeafNode(TextSegment segment) {
        return !segment.hasChildren();
    }

    private static class TextFieldProperty extends ReadOnly<String> {

        private final TextSegment.TextField field;

        public TextFieldProperty(TextSegment.TextField field) {
            super(field.getTag(), String.class, field.getTagDescription(), field.getTagDescription());
            this.field = field;
        }

        @Override
        public String getValue() throws IllegalAccessException, InvocationTargetException {
            return field.getContent();
        }
    }

    private class TypeProperty extends ReadOnly<ContentType> {

        private final AbstractStffNode node;

        public TypeProperty(AbstractStffNode node) {
            super("type", ContentType.class, "Type", "Type of segment");
            this.node = node;
        }

        @Override
        public ContentType getValue() throws IllegalAccessException, InvocationTargetException {
            return node.segment.getType();
        }
    }
}
