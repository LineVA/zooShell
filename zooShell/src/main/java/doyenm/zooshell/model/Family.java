package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public enum Family {

    UNKNOWN(0),
    LEMURIDAE(1);

    int id;

    Family(int id) {
        this.id = id;
    }
}
