package doyenm.zooshell.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class PaddockFacilitySpecie {

     @XmlElement(name = "facility")
    List<PaddockFacility> facilities;

    public PaddockFacilitySpecie(List<PaddockFacility> facilities) {
        this.facilities = facilities;
    }

    public PaddockFacilitySpecie() {
    }

}
