package hex.rpg.dto.in;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hln
 */
@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplementDTO {

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
    @XmlElement(name = "mediaType")
    public String mediaType;

}
