package hex.rpg.stf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hln
 */
public enum ContentType {

    CAMPAIGN,
    CAMPAIGN_SUPPLEMENT,
    EPISODE,
    EPISODE_SUPPLEMENT,
    NON_PLAYING_CHARACTER,
    NON_PLAYING_CHARACTER_SUPPLEMENT,
    PLAYER_NOTE,
    PLAYING_CHARACTER,
    PLAYING_CHARACTER_SUPPLEMENT,
    STORY,
    STORY_SUPPLEMENT;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String[] parts = name().split("_");
        for (String part : parts) {
            result.append(part.substring(0, 1)).append(part.substring(1).toLowerCase());
        }
        return result.toString();
    }

    public static ContentType getByString(String string) {
        for (ContentType type : values()) {
            if (type.toString().equals(string)) {
                return type;
            }
        }
        try {
            return valueOf(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public static List<String> getTypeStrings() {
        List<String> result = new ArrayList<>();
        for (ContentType type : values()) {
            result.add(type.toString());
        }
        return result;
    }
}
