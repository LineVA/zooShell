/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.evaluation.eventhandling.keeper;

import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum KeeperEventType {
    
    END_OF_TRAINING(EventCategory.UNARY, KeeperEventCategory.TRAINING,"The keeper {0} has finished their training");

    @Getter
    private EventCategory category;
    
    @Getter
    private KeeperEventCategory keeperEventCategory;

    @Getter
    private final String message;

    private KeeperEventType(EventCategory category, KeeperEventCategory keeperEventCategory, String message) {
        this.category = category;
        this.message = message;
        this.keeperEventCategory  = keeperEventCategory;
    }
}
