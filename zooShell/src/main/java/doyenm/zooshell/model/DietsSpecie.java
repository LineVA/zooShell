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
public class DietsSpecie {
   @XmlElement(name = "diet")
    List<Diet> diets;

    public DietsSpecie(List<Diet> diets) {
        this.diets = diets;
    }

    public DietsSpecie() {
    }
}
