package hex.rpg.app.domain.story;

import hex.rpg.app.domain.AbstractNarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 */
public class AppEpisode extends AbstractNarrativeEntity implements Episode {

    private Story story;
    private int index;
    private final List<Supplement> supplements = new ArrayList<>();
    private String content;

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof EpisodeSupplement) {
            ((EpisodeSupplement) supplement).setEpisode(this);
        }
        supplements.add(supplement);
    }

    @Override
    public List<Supplement> getSupplements() {
        Collections.sort(supplements);
        return supplements;
    }

    @Override
    public boolean hasSupplements() {
        return !supplements.isEmpty();
    }

    @Override
    public Long getParentId() {
        return story != null ? story.getId() : null;
    }

    @Override
    public Story getStory() {
        return story;
    }

    @Override
    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Episode) {
            return this.getIndex() - ((Episode) obj).getIndex();
        }
        return 0;
    }

}
