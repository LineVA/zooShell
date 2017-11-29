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
@Getter
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum Family {

    UNKNOWN(0),
    LEMURIDAE(1);

    int id;

    Family(int id) {
        this.id = id;
    }
}
