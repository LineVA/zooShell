package doyenm.zooshell.model;

import lombok.Getter;

/**
 * The diets
 * @author doyenm
 */
@Getter
public enum Diet {

    NONE(0),
    FOLIVOROUS(1),
    NECTARIVOROUS(2);

    int id;

    Diet(int id) {
        this.id = id;
    }
}

