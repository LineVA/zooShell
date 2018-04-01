package doyenm.zooshell.evaluation.animal;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.*;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDeathEvaluationControllerApplyTest {

    private Paddock givenPadWithType(PaddockType type) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getType()).thenReturn(type);
        return pad;
    }

    private LifespanAttributes givenLifespanAttributesWithValues(int female, int male) {
        LifespanAttributes attributes = Mockito.mock(LifespanAttributes.class);
        Mockito.when(attributes.getFemaleLifespan()).thenReturn(female);
        Mockito.when(attributes.getMaleLifespan()).thenReturn(male);
        return attributes;
    }

    private Animal givenAnimalWithPaddockSexLifespanAndAge(Paddock pad,
            Sex sex, LifespanAttributes attributes, int age) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        Mockito.when(animal.getLifespanAttributes()).thenReturn(attributes);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimal(Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.isDead()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setDead(Mockito.any(Boolean.class));
        return context;
    }

    
}
