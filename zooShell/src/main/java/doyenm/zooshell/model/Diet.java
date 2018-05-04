package doyenm.zooshell.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The diets
 * @author doyenm
 */
@Getter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Diet {

    NONE(0),
    CARNIVOROUS(1),
    FOLIVOROUS(2),
    FRUGIVOROUS(3),
    NECTARIVOROUS(4),
    OMNIVOROUS(5);

    int id;

    Diet(int id) {
        this.id = id;
    }
}

