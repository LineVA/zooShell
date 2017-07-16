package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalFastDaysEvaluationControllerApplyTest {

    private FoodAttributes givenFoodAttributesWithFastDays(int days) {
        FoodAttributes attributes = Mockito.mock(FoodAttributes.class);
        Mockito.when(attributes.getFastDays()).thenReturn(days);
        return attributes;
    }

    private Animal givenAnimalWithCurrentAndOptimalFooodAttrbutes(FoodAttributes current, FoodAttributes optimal) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCurrentFoodAttributes()).thenReturn(current);
        Mockito.when(animal.getOptimalFoodAttributes()).thenReturn(optimal);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimal(Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getFastDaysWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setFastDaysWellBeing(Mockito.anyDouble());
        return context;
    }

    @Test
    public void shouldSetTheWBLinkedToTheFastDaysToBaseWhenTheNumberOfFastDaysIsTheSameAsRequiredByTheSpecie() {
        // Given
        int days = TestUtils.generateInteger();
        FoodAttributes foodAttributes = givenFoodAttributesWithFastDays(days);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalFastDaysEvaluationController controller = new AnimalFastDaysEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getFastDaysWellBeing()).isEqualTo(context.getBase());
    }

    @Test
    public void shouldSetTheWBLinkedToTheFastDaysToZeroWhenTheNumberOfFastDaysIsNotTheSameThanRequiredByTheSpecie() {
        // Given
        int currentDays = TestUtils.generateInteger();
        int optimalDays = TestUtils.generateInteger();
        Assertions.assertThat(currentDays).isNotEqualTo(optimalDays);
        FoodAttributes currentFoodAttributes = givenFoodAttributesWithFastDays(currentDays);
        FoodAttributes optimalFoodAttributes = givenFoodAttributesWithFastDays(optimalDays);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(currentFoodAttributes, optimalFoodAttributes);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalFastDaysEvaluationController controller = new AnimalFastDaysEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getFastDaysWellBeing()).isEqualTo(context.getZero());
    }
}
