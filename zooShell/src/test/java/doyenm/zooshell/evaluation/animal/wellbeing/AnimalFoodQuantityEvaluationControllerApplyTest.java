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
       WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getFoodQuantityWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setFoodQuantityWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    private Utils givenUtilsWithIsBetweenAuthorizedValues(boolean ok) {
        Utils utils = Mockito.mock(Utils.class);
        Mockito.when(utils.isBetweenAuthorizedValues(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(ok);
        Mockito.when(utils.computeDeviationBetweenCurrentAndOptimal(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(RandomUtils.nextDouble());
        return utils;
    }

    @Test
    public void shouldSetTheWBLinkedToTheFoodQuantityToBaseTimesUICNCoefWhenCurrentFoodQuantityIsInTheAuthorizedValuesOfTheAnimal() {
        // Given
        double quantity = RandomUtils.nextInt();
        FoodAttributes foodAttributes = givenFoodAttributesWithFoodQuantity(quantity);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        double coef = RandomUtils.nextDouble();
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, coef, RandomUtils.nextDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(true);
        AnimalFoodQuantityEvaluationController controller = new AnimalFoodQuantityEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getFoodQuantityWellBeing()).isEqualTo(context.BASE*coef);
    }

    @Test
    public void shouldSetTheWBLinkedToTheFoodQuantityToZeroWhenCurrentFoodQuantityIsNotInTheAuthorizedValuesOfTheAnimal() {
        // Given
        double quantity = RandomUtils.nextInt();
        FoodAttributes foodAttributes = givenFoodAttributesWithFoodQuantity(quantity);
        Animal animal = givenAnimalWithCurrentAndOptimalFooodAttrbutes(foodAttributes, foodAttributes);
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, 
                RandomUtils.nextDouble(), RandomUtils.nextDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(false);
        AnimalFoodQuantityEvaluationController controller = new AnimalFoodQuantityEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getFoodQuantityWellBeing()).isEqualTo(context.ZERO);
    }
}
