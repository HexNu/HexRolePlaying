package hex.rpg.jpa.domain.story.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.EpisodeSupplement;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "Episode")
public class RpgEpisode implements Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column(length = 32 * Constants.KB)
    private String refereeInfo;
    @Column(length = Constants.KB)
    private String shortDescription;
    @Column(length = 32 * Constants.KB)
    private String description;
    @Column(length = 64 * Constants.KB)
    private String content;
    @Column(length = 8 * Constants.KB)
    private String refereeNotes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "episode", targetEntity = RpgEpisodeSupplement.class)
    private final List<Supplement> supplements = new ArrayList<>();
    @Column
    private int indexInStory;
    @ManyToOne(targetEntity = RpgStory.class)
    private Story story;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return getStory().getId();
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public void setRefereeInfo(String refereeInfo) {
        this.refereeInfo = refereeInfo;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }

    @Override
    public void setRefereeNotes(String refereeNotes) {
        this.refereeNotes = refereeNotes;
    }

    @Override
    public Story getStory() {
        return this.story;
    }

    @Override
    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public int getIndex() {
        return indexInStory;
    }

    @Override
    public void setIndex(int index) {
        this.indexInStory = index;
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
    public List<Supplement> getSupplements() {
        Collections.sort(supplements);
        return supplements;
    }

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof EpisodeSupplement) {
            ((EpisodeSupplement) supplement).setEpisode(this);
        }
        this.supplements.add(supplement);
    }

    @Override
    public boolean hasSupplements() {
        return !getSupplements().isEmpty();
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Episode) {
            return this.getIndex() - ((Episode) obj).getIndex();
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RpgEpisode other = (RpgEpisode) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
