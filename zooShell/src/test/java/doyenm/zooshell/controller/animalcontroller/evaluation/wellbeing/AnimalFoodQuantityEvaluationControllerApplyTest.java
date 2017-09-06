package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.AnimalWellBeing;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalFoodQuantityEvaluationControllerApplyTest {

    private FoodAttributes givenFoodAttributesWithFoodQuantity(double quantity) {
        FoodAttributes attributes = Mockito.mock(FoodAttributes.class);
        Mockito.when(attributes.getQuantity()).thenReturn(quantity);
        return attributes;
    }

    private Animal givenAnimalWithCurrentAndOptimalFooodAttrbutes(FoodAttributes current, FoodAttributes optimal) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCurrentFoodAttributes()).thenReturn(current);
        Mockito.when(animal.getOptimalFoodAttributes()).thenReturn(optimal);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimalUicnCoefficientAndStandard(Animal animal, double coef, double deviation) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getUicnCoefficient()).thenReturn(coef);
        Mockito.when(context.getUicnStandardDeviation()).thenReturn(deviation);
       AnimalWellBeing wb = Mockito.mock(AnimalWellBeing.class);
        Mockito.when(wb.getFoodQuantityWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setFoodQuantityWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    private Utils givenUtilsWithIsBetweenAuthorizedValues(boolean ok) {
        Utils utils = Mockito.mock(Utils.class);
        Mockito.when(utils.isBetweenAuthorizedValues(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(ok);
        Mockito.when(utils.computeDeviationBetweenCurrentAndOptimal(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(TestUtils.generateDouble());
        return utils;
    }

    @Test
    public void shouldSetTheWBLinkedToTheFoodQuantityToBaseTimesUICNCoefWhenCurrentFoodQuantityIsInTheAuthorizedValuesOfTheAnimal() {
        // Given
        double quantity = TestUtils.generateInteger();
        FoodAttributes foodAttributes = givenFoodAttributesWithFoodQuantity(quantity);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        double coef = TestUtils.generateDouble();
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, coef, TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(true);
        AnimalFoodQuantityEvaluationController controller = new AnimalFoodQuantityEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getFoodQuantityWellBeing()).isEqualTo(context.getBase()*coef);
    }

    @Test
    public void shouldSetTheWBLinkedToTheFoodQuantityToZeroWhenCurrentFoodQuantityIsNotInTheAuthorizedValuesOfTheAnimal() {
        // Given
        double quantity = TestUtils.generateInteger();
        FoodAttributes foodAttributes = givenFoodAttributesWithFoodQuantity(quantity);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, 
                TestUtils.generateDouble(), TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(false);
        AnimalFoodQuantityEvaluationController controller = new AnimalFoodQuantityEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getFoodQuantityWellBeing()).isEqualTo(context.getZero());
    }
}
