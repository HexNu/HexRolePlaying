package hex.rpg.api.modulesuport;

import java.io.InputStream;

/**
 *
 * @author hln
 */
public enum ResourceProvider {

    ICONS("icons");
    private final String resourcePath;
    
    private ResourceProvider(String resourcePath) {
        this.resourcePath = resourcePath;
    }
    
    public InputStream getResourceStream(String resource) {
        return ResourceProvider.class.getClassLoader().getResourceAsStream(resourcePath + "/" + resource);
    }
}
