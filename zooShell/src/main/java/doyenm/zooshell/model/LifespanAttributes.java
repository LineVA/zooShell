package doyenm.zooshell.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
