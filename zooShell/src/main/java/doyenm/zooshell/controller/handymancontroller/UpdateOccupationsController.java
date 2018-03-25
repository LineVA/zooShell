/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanUpdateOccupationsContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdateOccupationsController
        implements Function<HandymanUpdateOccupationsContext, HandymanUpdateOccupationsContext> {

    @Override
    public HandymanUpdateOccupationsContext apply(HandymanUpdateOccupationsContext t) {
        if(t.isAddition()){
            t.getHandyman().getAffectations().add(t.getPaddock());
        } else {
            t.getHandyman().getAffectations().remove(t.getPaddock());
        }
        return t;
    }

}
