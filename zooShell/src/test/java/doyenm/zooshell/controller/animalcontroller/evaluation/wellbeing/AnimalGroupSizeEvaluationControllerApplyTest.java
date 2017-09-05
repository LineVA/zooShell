package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.SocialAttributes;
import doyenm.zooshell.model.WellBeing;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalGroupSizeEvaluationControllerApplyTest {

    private SocialAttributes givenSocialAttributesWithGroupSize(int quantity) {
        SocialAttributes attributes = Mockito.mock(SocialAttributes.class);
        Mockito.when(attributes.getIndividualsPerGroup()).thenReturn(quantity);
        return attributes;
    }

    private Animal givenAnimalWithOptimalSocialAttributes(SocialAttributes optimal) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getSocialAttributes()).thenReturn(optimal);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimalUicnCoefficientAndStandard(Animal animal, double coef, double deviation) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getUicnCoefficient()).thenReturn(coef);
        Mockito.when(context.getUicnStandardDeviation()).thenReturn(deviation);
        Mockito.when(context.getGroupSizeWellBeing()).thenCallRealMethod();
        Mockito.when(context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock()).thenReturn(TestUtils.generateInteger());
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getSocialWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setSocialWellBeing(Mockito.anyDouble());
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
    public void shouldSetTheWBLinkedToGroupSizeToBaseTimesUICNCoefWhenCurrentGroupSizeIsInTheAuthorizedValuesOfTheAnimal() {
        // Given
        int quantity = TestUtils.generateInteger();
        SocialAttributes attributes = givenSocialAttributesWithGroupSize(quantity);
        Animal animal = givenAnimalWithOptimalSocialAttributes(attributes);
        double coef = TestUtils.generateDouble();
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, coef, TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(true);
        AnimalGroupSizeEvaluationController controller = new AnimalGroupSizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getSocialWellBeing()).isEqualTo(context.getBase() * coef);
    }

    @Test
    public void shouldSetTheWBLinkedToGroupSizeToZeroWhenCurrentGroupSizeIsNotInTheAuthorizedValuesOfTheAnimal() {
        // Given
        int quantity = TestUtils.generateInteger();
        SocialAttributes attributes = givenSocialAttributesWithGroupSize(quantity);
        Animal animal = givenAnimalWithOptimalSocialAttributes(attributes);
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal,
                TestUtils.generateDouble(), TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(false);
        AnimalGroupSizeEvaluationController controller = new AnimalGroupSizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getSocialWellBeing()).isEqualTo(context.getZero());
    }
}
