package hex.rpg.stf.out.segment;

import hex.rpg.stf.Field;
import java.util.Date;
import se.digitman.util.DateUtilities;

/**
 *
 * @author hln
 */
public class DocumentBuilder {

    private final StringBuilder result = new StringBuilder();

    public final DocumentBuilder append(String string) {
        result.append(string == null ? "" : string).append("\n");
        return this;
    }

    public final DocumentBuilder append(Long l) {
        result.append(l).append("\n");
        return this;
    }

    public final DocumentBuilder append(Integer i) {
        result.append(i).append("\n");
        return this;
    }

    public final DocumentBuilder append(Date date) {
        result.append(new DateUtilities(date).toString()).append("\n");
        return this;
    }

    public final DocumentBuilder append(Field.Label label) {
        result.append(label.tag());
        return this;
    }

    public final DocumentBuilder append(Class c) {
        result.append(c.getName().substring(c.getName().lastIndexOf(".") + 1)).append("\n");
        return this;
    }

    @Override
    public String toString() {
        return result.toString();
    }
}
