package doyenm.zooshell.model;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum PaddockArrangement {

    BRANCH_CHEW(0),
    CLIMBING_STRUCTURE(1),
    DIGGING_ZONE(2),
    ELEVATED_SLEEPING_ZONE(3),
    HANGING_ROOF(4),
    HIBERNATION_ZONE(5),
    POOL(6),
    SAND_POOL(7),
    SCRUB_TREE(8),
    SETBACK_ZONE(9),
    SWAMPY_ZONE(10);
    
    @Getter
    private int id;

    private PaddockArrangement(int id) {
        this.id = id;
    }
}
