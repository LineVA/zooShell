package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum PaddockType {

    UNKNOWN(0),
    AQUARIUM(1),
    AVIARY(2),
    CONTACT(3),
    ISLAND(4),
    LOWLAND(5),
    PIT(6),
    VIVARIUM(7);
    
    @Getter
    private int id;

    PaddockType(int id) {
        this.id = id;
    }
}
