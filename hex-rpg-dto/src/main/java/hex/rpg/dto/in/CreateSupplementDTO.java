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
@XmlRootElement(name = "supplement")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateSupplementDTO {

    @XmlElement(name = "id")
    public Long id;
    @XmlElement(name = "index")
    public Integer index;
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
    @XmlElement(name = "mediaType")
    public String mediaType;
    @XmlElement(name = "content")
    public byte[] content;
    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    @JsonProperty("links")
    public List<LinkDTO> links;

}
