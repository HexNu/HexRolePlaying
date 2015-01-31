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
@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateNonPlayingCharacterDTO {

    
    @XmlElement(name = "id")
    public Long id;
    @XmlElement(name = "name")
    public String name;
    @XmlElement(name = "shortDescription")
    public String shortDescription;
    @XmlElement(name = "description")
    public String description;
    @XmlElement(name = "birthdate")
    public String birthdate;
    @XmlElement(name = "gender")
    public String gender;
    @XmlElement(name = "habitation")
    public String habitation;
    @XmlElement(name = "occupation")
    public String occupation;
    @XmlElement(name = "species")
    public String species;
    @XmlElement(name = "mediaType")
    public String mediaType;
    @XmlElement(name = "refereeInfo")
    public String refereeInfo;
    @XmlElement(name = "refereeNotes")
    public String refereeNotes;
    @XmlElementWrapper(name = "supplements")
    @XmlElement(name = "supplement")
    @JsonProperty("supplements")
    public List<CreateSupplementDTO> supplements;
    @XmlElementWrapper(name = "links")
    @XmlElement(name = "link")
    @JsonProperty("links")
    public List<LinkDTO> links;

    

}
