package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum PaddockFacility {

    NONE(0),
    BRANCH_CHEW(1),
    CLIMBING_STRUCTURE(2),
    DIGGING_ZONE(3),
    ELEVATED_SLEEPING_ZONE(4),
    HANGING_ROOF(5),
    HIBERNATION_ZONE(6),
    POOL(7),
    SAND_POOL(8),
    SCRUB_TREE(9),
    SETBACK_ZONE(10),
    SWAMPY_ZONE(11);
    
    @Getter
    private int id;

    private PaddockFacility(int id) {
        this.id = id;
    }
}
