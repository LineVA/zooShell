package doyenm.zooshell.evaluation.paddock;

import doyenm.zooshell.evaluation.PaddockEvaluationContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

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
    List<Handyman> handymen;

    public static LightZooDto makeLightZooDto(PaddockEvaluationContext context){
         int animals = (int) context.getZoo().getAnimals().values()
                .stream()
                .filter(animal -> animal.getPaddock().equals(context.getPaddock()))
                .count();
         List<Handyman> handymenInThePaddock = context.getZoo().getHandymen().values()
                 .stream()
                 .filter(hd -> hd.getAffectations().contains(context.getPaddock()))
                 .collect(Collectors.toList());
        return new LightZooDto(context.getPaddock(),
                context.getZoo().getMonthsPerEvaluation(),
                animals, handymenInThePaddock);
    }
    
    
}
