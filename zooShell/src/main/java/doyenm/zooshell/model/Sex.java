package doyenm.zooshell.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author doyenm
 */
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Sex {

    UNKNOWN(0),
    FEMALE(1),
    MALE(2);

    @Getter
    private int id;

    Sex(int id) {
        this.id = id;
    }
}
