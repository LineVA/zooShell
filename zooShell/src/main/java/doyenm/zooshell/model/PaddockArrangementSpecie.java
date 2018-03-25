package doyenm.zooshell.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class PaddockArrangementSpecie {

     @XmlElement(name = "arrangement")
    List<PaddockArrangement> arrangements;

    public PaddockArrangementSpecie(List<PaddockArrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public PaddockArrangementSpecie() {
    }

}
