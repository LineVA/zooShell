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
public class ReproductionAttributes {

    private int femaleMaturityAge;
    private int maleMaturityAge;
    private double frequency;
    private int litterSize;
    private int gestationDuration;
    private int weaningAge;
    
    public int getMaturityGivenSex(Sex sex) {
        return Sex.FEMALE == sex
                ? getFemaleMaturityAge() : getMaleMaturityAge();
    }
}
