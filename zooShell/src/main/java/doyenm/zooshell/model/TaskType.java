package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public enum TaskType {

    UNKNOWN(0),
    BREEDING(1),
    CLEANING(2),
    ENRICHMENT(3),
    FEEDING(4),
    MEDICAL_TRAINING(5);
    
    int id;

    TaskType(int id) {
        this.id = id;
    }

}
