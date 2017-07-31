package doyenm.zooshell.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author doyenm
 */
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum PaddockType {

    UNKNOWN(0),
    AQUARIUM(1),
    AVIARY(2),
    CONTACT(3),
    ISLAND(4),
    LOWLAND(5),
    PIT(6),
    VIVARIUM(7);
    
    @Getter
    private int id;

    PaddockType(int id) {
        this.id = id;
    }
}
