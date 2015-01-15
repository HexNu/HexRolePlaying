package hex.rpg.core.domain.story.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.impl.RpgCampaign;
import hex.rpg.core.domain.story.Episode;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.StorySupplement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
@Table(name = "Story")
public class RpgStory implements Story {

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
    @Column(length = 8 * Constants.KB)
    private String refereeNotes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "story", targetEntity = RpgEpisode.class)
    private final Set<Episode> episodes = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "story", targetEntity = RpgStorySupplement.class)
    private final Set<Supplement> supplements = new HashSet<>();
    @Column
    private int indexInCampaign;
    @ManyToOne(targetEntity = RpgCampaign.class)
    private Campaign campaign;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Long getParentId() {
        return getCampaign().getId();
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
    public Campaign getCampaign() {
        return campaign;
    }

    @Override
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    @Override
    public List<Episode> getEpisodes() {
        List<Episode> result = new ArrayList<>(episodes);
        Collections.sort(result);
        return result;
    }

    @Override
    public void addEpisode(int index, Episode episode) {
        this.episodes.add(episode);
        episode.setStory(this);
        episode.setIndex(index);
        Iterator<Episode> iterator = episodes.iterator();
        while (iterator.hasNext()) {
            Episode e = iterator.next();
            if (!Objects.equals(e.getId(), episode.getId()) && e.getIndex() >= index) {
                index++;
                e.setIndex(index);
            }
        }
    }

    @Override
    public void addEpisode(Episode episode) {
        addEpisode(episodes.size(), episode);
    }

    @Override
    public int getIndex() {
        return indexInCampaign;
    }

    @Override
    public void setIndex(int index) {
        this.indexInCampaign = index;
    }

    @Override
    public List<Supplement> getSupplements() {
        return new ArrayList<>(supplements);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof StorySupplement) {
            ((StorySupplement) supplement).setStory(this);
        }
        this.supplements.add(supplement);
    }

    @Override
    public boolean hasSupplements() {
        return !getSupplements().isEmpty();
    }

    @Override
    public int compareTo(Object obj) {
        if (obj instanceof Story) {
            return this.getIndex() - ((Story) obj).getIndex();
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final RpgStory other = (RpgStory) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
