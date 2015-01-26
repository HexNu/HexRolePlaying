package hex.rpg.dto.in;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
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
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoryDTO {

    @XmlElement(name = "id")
    public Long id;
    @XmlElement(name = "title")
    public String title;
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
    @XmlElementWrapper(name = "episodes")
    @XmlElement(name = "episode")
    @JsonProperty("episodes")
    public List<EpisodeDTO> episodes = new ArrayList<>();
    @XmlElementWrapper(name = "supplements")
    @XmlElement(name = "supplement")
    @JsonProperty("supplements")
    public List<SupplementDTO> supplements = new ArrayList<>();
    
    public StoryDTO() {
    }

}
