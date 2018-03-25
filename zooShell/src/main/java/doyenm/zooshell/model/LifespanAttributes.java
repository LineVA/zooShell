package doyenm.zooshell.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LifespanAttributes {

    private int femaleLifespan;
    private int maleLifespan;

     public int getLifespanGivenSex(Sex sex) {
        return Sex.FEMALE == sex
                ? getFemaleLifespan(): getMaleLifespan();
    }
}
