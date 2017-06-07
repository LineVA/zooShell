package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public enum TaskType {

    UNKNOWN(0),
    CLEANING(1),
    ENRICHMENT(2),
    FEEDING(3),
    MEDICAL_TRAINING(4),
    NURSING(6);

    int id;

    TaskType(int id) {
        this.id = id;
    }

}
