package hex.rpg.io.file;

import hex.rpg.core.Constants;
import java.io.File;

/**
 *
 * @author hln
 */
public interface FileWriter {

    String DEFAULT_ENCODING = Constants.UTF_8;

    File write();
}
