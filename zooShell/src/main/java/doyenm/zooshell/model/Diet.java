package doyenm.zooshell.model;

import lombok.Getter;

/**
 * The diets
 * @author doyenm
 */
@Getter
public enum Diet {

    NONE(0),
    CARNIVOROUS(1),
    FOLIVOROUS(2),
    NECTARIVOROUS(3);

    int id;

    Diet(int id) {
        this.id = id;
    }
}

