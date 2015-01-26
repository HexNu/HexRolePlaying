package hex.rpg.app.domain.campaign;

import hex.rpg.app.domain.AbstractNarrativeEntity;
import hex.rpg.core.domain.Supplement;
import hex.rpg.core.domain.campaign.Campaign;
import hex.rpg.core.domain.campaign.CampaignSupplement;
import hex.rpg.core.domain.character.NonPlayingCharacter;
import hex.rpg.core.domain.story.Story;
import java.util.ArrayList;
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
    private boolean hasStories = true;
    private boolean hasCharacters = true;
    private boolean hasSupplements = true;

    @Override
    public void addSupplement(Supplement supplement) {
        if (supplement instanceof CampaignSupplement) {
            ((CampaignSupplement) supplement).setCampaign(this);
        }
        supplements.add(supplement);
    }

    @Override
    public List<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public boolean hasSupplements() {
        return !hasSupplements ? !supplements.isEmpty() : true;
    }

    public void setHasSupplements(boolean hasSupplements) {
        this.hasSupplements = hasSupplements;
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
        stories.add(index, story);
    }

    @Override
    public void addStory(Story story) {
        story.setCampaign(this);
        stories.add(story);
    }

    @Override
    public boolean hasStories() {
        return !hasStories ? !stories.isEmpty() : true;
    }

    public void setHasStories(boolean hasStories) {
        this.hasStories = hasStories;
    }

    @Override
    public List<NonPlayingCharacter> getCharacters() {
        return characters;
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
        return !hasCharacters ? !characters.isEmpty() : true;
    }

    public void setHasCharacters(boolean hasCharacters) {
        this.hasCharacters = hasCharacters;
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
