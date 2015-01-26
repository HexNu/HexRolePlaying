package nu.hex.filehandler.node;

import hex.rpg.stf.ContentType;
import hex.rpg.stf.Field;
import hex.rpg.stf.Segment;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public class TextSegment {

    private final String segment;
    private ContentType type;
    private Integer id = null;
    private Integer parentId = null;
    private final List<TextField> fields = new ArrayList<>();
    private final List<TextSegment> children = new ArrayList<>();
    private TextSegment parent;

    public TextSegment(String segment) {
        this.segment = segment;
        parseLines();
    }

    private void parseLines() {
        TextField field = null;
        for (String line : segment.split("\n")) {
            if (ContentType.getTypeStrings().contains(line)) {
                type = ContentType.getByString(line);
            } else if (line.startsWith(Field.Label.x.tag())) {
                String value = line.replaceAll(Field.Label.x.tag(), "").trim();
                id = !value.equals("") ? Integer.valueOf(value) : null;
            } else if (line.startsWith(Field.Label.p.tag())) {
                String value = line.replaceAll(Field.Label.p.tag(), "").trim();
                parentId = !value.equals("") ? Integer.valueOf(value) : null;
            }
            if (line.matches(Field.Label.regEx())) {
                if (field != null) {
                    fields.add(field);
                }
                field = new TextField(line);
            } else {
                if (field != null) {
                    field.append(line);
                }
            }
        }
        if (field != null && !fields.contains(field)) {
            fields.add(field);
        }
    }

    public ContentType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public List<TextField> getFields() {
        return fields;
    }
    
    public String getName() {
        for (TextField field : fields) {
            if (field.getTag().equals(Field.Label.T.tag())) {
                return field.getContent();
            }
        }
        return getType().toString();
    }

    public TextSegment getParent() {
        return parent;
    }

    public void setParent(TextSegment parent) {
        this.parent = parent;
    }

    public void addChildren(List<TextSegment> children) {
        this.children.addAll(children);
    }

    public void addChild(TextSegment child) {
        children.add(child);
    }

    public List<TextSegment> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public class TextField {

        private final String tag;
        private final StringBuilder content;

        public TextField(String line) {
            this.tag = line.substring(0, line.indexOf(Field.LABEL_SEPARATOR) + 1);
            this.content = new StringBuilder(line.substring(line.indexOf(Field.LABEL_SEPARATOR) + 1)).append("\n");
        }

        public String getTag() {
            return tag;
        }
        
        public String getTagDescription() {
            return Field.Label.getByTag(tag).getDescription();
        }

        public void append(String line) {
            this.content.append(line).append("\n");
        }

        public String getContent() {
            return content.toString();
        }
    }
}
