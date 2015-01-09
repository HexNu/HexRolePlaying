package hex.rpg.core.domain.story;

import hex.rpg.core.domain.NarrativeEntity;

/**
 *
 * @author hln
 */
public interface Episode extends NarrativeEntity, Comparable {

    Story getStory();

    void setStory(Story story);
    
    int getIndex();
    
    void setIndex(int index);

    String getContent();

    void setContent(String content);
}
