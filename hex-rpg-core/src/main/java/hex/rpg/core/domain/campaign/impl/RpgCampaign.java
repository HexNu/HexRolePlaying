package hex.rpg.core.domain.campaign.impl;

import hex.rpg.core.Constants;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.story.Story;
import hex.rpg.core.domain.story.impl.RpgStory;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hln
 */
@Entity
@Table(name = "Campaign")
public class RpgCampaign implements Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @Column(length = 32 * Constants.KB)
    private String refereeInfo;
    @Column(length = Constants.KB)
    private String shortDescription;
    @Column(length = 32 * Constants.KB)
    private String description;
    @Column(length = 8 * Constants.KB)
    private String refereeNotes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "campaign", targetEntity = RpgStory.class)
    private final Set<Story> stories = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "campaign", targetEntity = RpgCampaignSupplement.class)
    private final Set<Supplement> supplements = new HashSet<>();

    @Override
    public Long getId() {
        return id;
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
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
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
    public List<Story> getStories() {
        List<Story> result = new ArrayList<>(stories);
        Collections.sort(result);
        return result;
    }

    @Override
    public void addStory(int index, Story story) {
        if (!stories.contains(story)) {
            stories.add(story);
            story.setCampaign(this);
            story.setIndex(index);
            Iterator<Story> iterator = stories.iterator();
            while (iterator.hasNext()) {
                Story s = iterator.next();
                if (!Objects.equals(s.getId(), story.getId()) && s.getIndex() >= index) {
                    index++;
                    s.setIndex(index);
                }
            }
        }
    }

    @Override
    public void addStory(Story story) {
        addStory(stories.size(), story);
    }

    @Override
    public List<Supplement> getSupplements() {
        return new ArrayList<>(supplements);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof CampaignSupplement) {
            ((CampaignSupplement) supplement).setCampaign(this);
        }
        this.supplements.add(supplement);
    }
}
