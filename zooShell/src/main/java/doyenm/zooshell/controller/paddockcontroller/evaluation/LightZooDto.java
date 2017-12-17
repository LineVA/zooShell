package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@AllArgsConstructor
@Getter
public class LightZooDto {
    Paddock paddock;
    int speed;
    int animalsOfThePaddock;

    public static LightZooDto makeLightZooDto(PaddockEvaluationContext context){
         int animals = (int) context.getZoo().getAnimals().values()
                .stream()
                .filter(animal -> animal.getPaddock().equals(context.getPaddock()))
                .count();
        return new LightZooDto(context.getPaddock(),
                context.getZoo().getMonthsPerEvaluation(),
                animals);
    }
    
    
}
