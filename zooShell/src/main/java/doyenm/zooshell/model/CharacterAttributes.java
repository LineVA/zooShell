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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class CharacterAttributes {

    private double agressivity;
    private double bravery;
    private double cohabitationFactor;
    private double curiosity;
    private double gourmandise;
    private double inteligence;
    private double meticulousness;

    

}
