package hex.rpg.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hln
 */
@XmlRootElement(name = "campaign")
@XmlAccessorType(XmlAccessType.FIELD)
public class CampaignDTO implements EntityDTO {

    @XmlElement(name = "id")
    public Long id;
    @XmlElement(name = "title")
    public String title;
    @XmlElement(name = "name")
    public String name;
    @XmlElement(name = "type")
    public String type;
    @XmlElement(name = "shortDescription")
    public String shortDescription;
    @XmlElement(name = "description")
    public String description;
    @XmlElement(name = "refereeInfo")
    public String refereeInfo;
    @XmlElement(name = "refereeNotes")
    public String refereeNotes;
    @XmlElementWrapper(name = "supplements")
    @XmlElement(name = "supplement")
    @JsonProperty("supplements")
    public List<SupplementDTO> supplements;
    @XmlElementWrapper(name = "stories")
    @XmlElement(name = "story")
    @JsonProperty("stories")
    public List<StoryDTO> stories;
    @XmlElementWrapper(name = "characters")
    @XmlElement(name = "character")
    @JsonProperty("characters")
    public List<StoryDTO> characters;

    public CampaignDTO() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getRefereeInfo() {
        return refereeInfo;
    }

    @Override
    public String getRefereeNotes() {
        return refereeNotes;
    }
}
