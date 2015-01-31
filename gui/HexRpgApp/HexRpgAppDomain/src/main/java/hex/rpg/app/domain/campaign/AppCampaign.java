package hex.rpg.app.domain.campaign;

import hex.rpg.app.domain.AbstractNarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hln
 */
public class AppCampaign extends AbstractNarrativeEntity implements Campaign {

    private Campaign.Type type;
    private final List<Story> stories = new ArrayList<>();
    private final List<Supplement> supplements = new ArrayList<>();
    private final List<NonPlayingCharacter> characters = new ArrayList<>();

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof CampaignSupplement) {
            ((CampaignSupplement) supplement).setCampaign(this);
        }
        supplements.add(supplement);
    }

    @Override
    public List<Supplement> getSupplements() {
        Collections.sort(supplements);
        return supplements;
    }
    
    public void setSupplements(List<Supplement> supplements) {
        this.supplements.addAll(supplements);
    }

    @Override
    public boolean hasSupplements() {
        return !supplements.isEmpty();
    }

    @Override
    public Long getParentId() {
        return null;
    }

    @Override
    public List<Story> getStories() {
        return stories;
    }

    @Override
    public void addStory(int index, Story story) {
        story.setCampaign(this);
        story.setIndex(index);
        stories.add(index, story);
    }

    @Override
    public void addStory(Story story) {
        addStory(this.stories.size(), story);
    }
    
    @Override
    public boolean hasStories() {
        return !stories.isEmpty();
    }

    @Override
    public List<NonPlayingCharacter> getCharacters() {
        return characters;
    }
    
    public void setCharacters(List<NonPlayingCharacter> characters) {
        this.characters.addAll(characters);
    }

    @Override
    public void addCharacters(List<NonPlayingCharacter> characters) {
        characters.stream().forEach((character) -> {
            character.setCampaign(this);
            this.characters.add(character);
        });
    }

    @Override
    public void addCharacter(NonPlayingCharacter character) {
        characters.add(character);
        character.setCampaign(this);
    }

    @Override
    public boolean hasCharacters() {
        return !characters.isEmpty();
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
