package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class ExecuteReproductionFunctionApplyTest {

    private Zoo givenZoo() {
        return Mockito.mock(Zoo.class);
    }

    private Animal givenAnimal() {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setMonthsOfGestation(Mockito.anyInt());
        Mockito.when(animal.getMonthsOfGestation()).thenCallRealMethod();
        return animal;
    }

    private CalvingFunction givenCalvingFunctionWithContext(AnimalEvaluationContext context) {
        CalvingFunction function = Mockito.mock(CalvingFunction.class);
        Mockito.when(function.apply(Mockito.any(AnimalEvaluationContext.class))).thenReturn(context);
        return function;
    }

    private AnimalEvaluationContext givenContextWithZooSpeedAnimalGestationDurationAndCurrentDuration(
            Zoo zoo, int speed, Animal animal, int optimal, int current) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getMonthsPerTurn()).thenReturn(speed);
        Mockito.when(context.getCurrentGestationDuration()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setCurrentGestationDuration(Mockito.anyInt());
        context.setCurrentGestationDuration(current);
        Mockito.when(context.getGestationDuration()).thenReturn(optimal);
        return context;
    }

    @Test
    public void shouldSeTheCurrentGestationDurationToZeroWhenItIsOverAndItHasBegunDuringThisTurn() {
        // Given
        int currentGestationDuration = 0;
        int gestationDuration = 6;
        Animal animal = givenAnimal();
        int speed = 6;
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithZooSpeedAnimalGestationDurationAndCurrentDuration(
                zoo, speed, animal, gestationDuration, currentGestationDuration);
        CalvingFunction calvingFunction = givenCalvingFunctionWithContext(context);
        ExecuteReproductionFunction function = new ExecuteReproductionFunction(calvingFunction);
        // When
        AnimalEvaluationContext actualContext = function.apply(context);
        // Then
        Assertions.assertThat(actualContext.getCurrentGestationDuration()).isEqualTo(0);
    }
    
    @Test
    public void shouldSeTheCurrentGestationDurationToZeroWhenItIsOverAndItHasBegunBeforeThisTurn() {
        // Given
        int currentGestationDuration = 3;
        int gestationDuration = 6;
        Animal animal = givenAnimal();
        int speed = 5;
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithZooSpeedAnimalGestationDurationAndCurrentDuration(
                zoo, speed, animal, gestationDuration, currentGestationDuration);
        CalvingFunction calvingFunction = givenCalvingFunctionWithContext(context);
        ExecuteReproductionFunction function = new ExecuteReproductionFunction(calvingFunction);
        // When
        AnimalEvaluationContext actualContext = function.apply(context);
        // Then
        Assertions.assertThat(actualContext.getCurrentGestationDuration()).isEqualTo(0);
    }
    
    @Test
    public void shouldSeTheCurrentGestationDurationWhenItIsNotOverAndItHasBegunDuringThisTurn() {
        // Given
        int currentGestationDuration = 0;
        int gestationDuration = 22;
        Animal animal = givenAnimal();
        int speed = 6;
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithZooSpeedAnimalGestationDurationAndCurrentDuration(
                zoo, speed, animal, gestationDuration, currentGestationDuration);
        CalvingFunction calvingFunction = givenCalvingFunctionWithContext(context);
        ExecuteReproductionFunction function = new ExecuteReproductionFunction(calvingFunction);
        // When
        AnimalEvaluationContext actualContext = function.apply(context);
        // Then
        Assertions.assertThat(actualContext.getCurrentGestationDuration()).isEqualTo(6);
    }
    
    @Test
    public void shouldSeTheCurrentGestationDurationToZeroWhenItIsNotOverAndItHasBegunBeforeThisTurn() {
        // Given
        int currentGestationDuration = 6;
        int gestationDuration = 22;
        Animal animal = givenAnimal();
        int speed = 6;
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithZooSpeedAnimalGestationDurationAndCurrentDuration(
                zoo, speed, animal, gestationDuration, currentGestationDuration);
        CalvingFunction calvingFunction = givenCalvingFunctionWithContext(context);
        ExecuteReproductionFunction function = new ExecuteReproductionFunction(calvingFunction);
        // When
        AnimalEvaluationContext actualContext = function.apply(context);
        // Then
        Assertions.assertThat(actualContext.getCurrentGestationDuration()).isEqualTo(12);
    }
}
