package doyenm.zooshell.controller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalEvaluationController;
import doyenm.zooshell.controller.handymancontroller.HandymanEvaluationController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockEvaluationController;
import doyenm.zooshell.controller.zoocontroller.ZooEvaluationController;
import doyenm.zooshell.zoo.PenaltiesEvaluationController;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class EvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final AnimalEvaluationController animalEvaluationController;
    private final KeeperEvaluationController keeperEvaluationController;
    private final PaddockEvaluationController paddockEvaluationController;
    private final HandymanEvaluationController handymanEvaluationController;
    private final ZooEvaluationController zooEvaluationController;
    private final PenaltiesEvaluationController penaltiesEvaluationController;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context = Stream.of(context)
                .map(zooEvaluationController)
                .map(paddockEvaluationController)
                .map(animalEvaluationController)
                .map(keeperEvaluationController)
                .map(handymanEvaluationController)
                .map(penaltiesEvaluationController)
                .map((EvaluationContext t1) -> {
                    t1.getZoo().setGradeObj(t1.getGradeObj());
                    t1.getZoo().setGrade(t1.getZoo().getGradeObj().computeGrade());
                    return t1;
                })
                .findFirst()
                .get();
        context.updateAnimals();
        return context;
    }
}
