/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.controller.eventhandling.keeper;

import doyenm.zooshell.controller.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum KeeperEventType {
    
    PREGNANCY_PURSUIT(EventCategory.UNARY, KeeperEventCategory.TRAINING,"The keeper {0} hasfinished their training");

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
