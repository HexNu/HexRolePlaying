package hex.rpg.service.support;

/**
 * Created 2014-12-10
 *
 * @author hl
 */
public class PuHandlerFactory {

    public static final String RPG_DATABASE = "rpg_";
    private final EmHandlerCache cache = new EmHandlerCache();

    private EmHandler getRolePlayingHandler(String key) {
        return cache.getHandler(EmHandler.Type.RPG, RPG_DATABASE + key.toUpperCase());
    }

    public EmHandler get(String key) {
        return getRolePlayingHandler(key);
    }

    public void kill(String key) {
        if (key != null) {
            cache.removeHandler(RPG_DATABASE + key);
        }
    }
}
