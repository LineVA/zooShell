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
    ENRCHMENT(2),
    FEEDING(3),
    MEDICAL_TRAINING(4);
    
    int id;

    TaskType(int id) {
        this.id = id;
    }

}
