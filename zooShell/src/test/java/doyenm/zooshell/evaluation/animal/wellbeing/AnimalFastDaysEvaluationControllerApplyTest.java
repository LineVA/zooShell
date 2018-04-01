package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.WellBeing;
import org.apache.commons.lang.math.RandomUtils;
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
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getFastDaysWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setFastDaysWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    @Test
    public void shouldSetTheWBLinkedToTheFastDaysToBaseWhenTheNumberOfFastDaysIsTheSameAsRequiredByTheSpecie() {
        // Given
        int days = RandomUtils.nextInt();
        FoodAttributes foodAttributes = givenFoodAttributesWithFastDays(days);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalFastDaysEvaluationController controller = new AnimalFastDaysEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getFastDaysWellBeing()).isEqualTo(context.BASE);
    }

    @Test
    public void shouldSetTheWBLinkedToTheFastDaysToZeroWhenTheNumberOfFastDaysIsNotTheSameThanRequiredByTheSpecie() {
        // Given
        int currentDays = RandomUtils.nextInt();
        int optimalDays = RandomUtils.nextInt();
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
        Assertions.assertThat(actualContext.getWellBeingObj().getFastDaysWellBeing()).isEqualTo(context.ZERO);
    }
}