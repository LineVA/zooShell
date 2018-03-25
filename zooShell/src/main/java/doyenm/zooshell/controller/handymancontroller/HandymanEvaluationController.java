/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.HandymanEvaluationContext;
import doyenm.zooshell.controller.handymancontroller.evaluation.HandymanAgeEvaluationController;
import doyenm.zooshell.model.Handyman;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanEvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final HandymanAgeEvaluationController handymanAgeEvaluationController;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.setEvaluatedHandymenList(context.getHandymen().values()
                .stream()
                .map((Handyman t1) -> new HandymanEvaluationContext(context.getZoo(), t1))
                // Age
                .map(handymanAgeEvaluationController)
                .map(HandymanEvaluationContext::getHandyman)
                .collect(Collectors.toList())
        );
        return context;
    }

}
