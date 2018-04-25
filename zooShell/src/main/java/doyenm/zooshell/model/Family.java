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
@Getter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Family {

    UNKNOWN(0),
    LEMURIDAE(1),
    PROCYONIDAE(2),
    URSIDAE(3);

    int id;

    Family(int id) {
        this.id = id;
    }
}
