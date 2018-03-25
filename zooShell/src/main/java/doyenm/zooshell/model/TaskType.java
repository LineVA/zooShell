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
public enum TaskType {

    UNKNOWN(0),
    CLEANING(1),
    ENRICHMENT(2),
    FEEDING(3),
    MEDICAL_TRAINING(4),
    NURSING(5);

    int id;

    TaskType(int id) {
        this.id = id;
    }

}
