package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
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
