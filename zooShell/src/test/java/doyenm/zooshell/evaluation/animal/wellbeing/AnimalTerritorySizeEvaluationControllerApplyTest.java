package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.*;
import org.apache.commons.lang.math.RandomUtils;
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
        Mockito.when(context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock()).thenReturn(RandomUtils.nextInt());
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getTerritoryWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setTerritoryWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getHeight()).thenReturn(RandomUtils.nextInt());
        Mockito.when(pad.getWidth()).thenReturn(RandomUtils.nextInt());
        Mockito.when(context.getPaddock()).thenReturn(pad);
        return context;
    }

    private Utils givenUtilsWithIsBetweenAuthorizedValues(boolean ok) {
        Utils utils = Mockito.mock(Utils.class);
        Mockito.when(utils.isBetweenAuthorizedValues(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(ok);
        Mockito.when(utils.computeDeviationBetweenCurrentAndOptimal(Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(RandomUtils.nextDouble());
        return utils;
    }

    @Test
    public void shouldSetTheWBLinkedToGroupSizeToBaseTimesUICNCoefWhenCurrentGroupSizeIsInTheAuthorizedValuesOfTheAnimal() {
        // Given
        TerritoryAttributes territoryAttributes = givenTerritoryAttributesWithGroupSize(RandomUtils.nextInt());
        SocialAttributes socialAttributes = givenSocialAttributesWithGroupSize(RandomUtils.nextInt());
        Animal animal = givenAnimalWithOptimalTerritoryAndOptimalSocialAttributes(territoryAttributes, socialAttributes);
        double coef = RandomUtils.nextDouble();
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal, coef, RandomUtils.nextDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(true);
        AnimalTerritorySizeEvaluationController controller = new AnimalTerritorySizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getTerritoryWellBeing()).isEqualTo(context.BASE * coef);
    }

    @Test
    public void shouldSetTheWBLinkedToGroupSizeToZeroWhenCurrentGroupSizeIsNotInTheAuthorizedValuesOfTheAnimal() {
        // Given
        TerritoryAttributes territoryAttributes = givenTerritoryAttributesWithGroupSize(RandomUtils.nextInt());
        SocialAttributes socialAttributes = givenSocialAttributesWithGroupSize(RandomUtils.nextInt());
        Animal animal = givenAnimalWithOptimalTerritoryAndOptimalSocialAttributes(territoryAttributes, socialAttributes);
        AnimalEvaluationContext context = givenContextWithAnimalUicnCoefficientAndStandard(animal,
               RandomUtils.nextDouble(), RandomUtils.nextDouble());
        Utils utils = givenUtilsWithIsBetweenAuthorizedValues(false);
        AnimalTerritorySizeEvaluationController controller = new AnimalTerritorySizeEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getTerritoryWellBeing()).isEqualTo(context.ZERO);
    }
}
