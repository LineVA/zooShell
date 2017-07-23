package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.SocialAttributes;
import doyenm.zooshell.model.TerritoryAttributes;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalTerritorySizeEvaluationControllerApplyTest {

    private TerritoryAttributes givenTerritoryAttributesWithGroupSize(int quantity) {
        TerritoryAttributes attributes = Mockito.mock(TerritoryAttributes.class);
        Mockito.when(attributes.getTerritorySizeForOneGroup()).thenReturn(quantity);
        return attributes;
    }

    private SocialAttributes givenSocialAttributesWithGroupSize(int quantity) {
        SocialAttributes attributes = Mockito.mock(SocialAttributes.class);
        Mockito.when(attributes.getIndividualsPerGroup()).thenReturn(quantity);
        return attributes;
    }

    private Animal givenAnimalWithOptimalTerritoryAndOptimalSocialAttributes(
            TerritoryAttributes optimalTerritory, SocialAttributes optimalSocial) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getTerritoryAttributes()).thenReturn(optimalTerritory);
        Mockito.when(animal.getSocialAttributes()).thenReturn(optimalSocial);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimalUicnCoefficientAndStandard(Animal animal, double coef, double deviation) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getUicnCoefficient()).thenReturn(coef);
        Mockito.when(context.getUicnStandardDeviation()).thenReturn(deviation);
        Mockito.when(context.getGroupSizeWellBeing()).thenCallRealMethod();
        Mockito.when(context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock()).thenReturn(TestUtils.generateInteger());
        Mockito.doCallRealMethod().when(context).setGroupSizeWellBeing(Mockito.anyDouble());
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getHeight()).thenReturn(TestUtils.generateInteger());
        Mockito.when(pad.getWidth()).thenReturn(TestUtils.generateInteger());
        Mockito.when(context.getPaddock()).thenReturn(pad);
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
        TerritoryAttributes territoryAttributes = givenTerritoryAttributesWithGroupSize(TestUtils.generateInteger());
        SocialAttributes socialAttributes = givenSocialAttributesWithGroupSize(TestUtils.generateInteger());
        Animal animal = givenAnimalWithOptimalTerritoryAndOptimalSocialAttributes(territoryAttributes, socialAttributes);
        double coef = TestUtils.generateDouble();
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, coef, TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(true);
        AnimalTerritorySizeEvaluationController controller = new AnimalTerritorySizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getFoodQuantityWellBeing()).isEqualTo(context.getBase() * coef);
    }

    @Test
    public void shouldSetTheWBLinkedToGroupSizeToZeroWhenCurrentGroupSizeIsNotInTheAuthorizedValuesOfTheAnimal() {
        // Given
        TerritoryAttributes territoryAttributes = givenTerritoryAttributesWithGroupSize(TestUtils.generateInteger());
        SocialAttributes socialAttributes = givenSocialAttributesWithGroupSize(TestUtils.generateInteger());
        Animal animal = givenAnimalWithOptimalTerritoryAndOptimalSocialAttributes(territoryAttributes, socialAttributes);
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal,
                TestUtils.generateDouble(), TestUtils.generateDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(false);
        AnimalTerritorySizeEvaluationController controller = new AnimalTerritorySizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getFoodQuantityWellBeing()).isEqualTo(context.getZero());
    }
}