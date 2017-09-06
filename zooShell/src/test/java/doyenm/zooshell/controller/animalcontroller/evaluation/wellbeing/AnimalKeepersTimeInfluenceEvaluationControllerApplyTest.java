package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.AnimalWellBeing;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalKeepersTimeInfluenceEvaluationControllerApplyTest {

    private CharacterAttributes givenCharacterAttributesWithBravery(double bravery) {
        CharacterAttributes attributes = Mockito.mock(CharacterAttributes.class);
        Mockito.when(attributes.getBravery()).thenReturn(bravery);
        return attributes;
    }

    private Animal givenAnimalWithCharacterAttributes(CharacterAttributes optimal) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCharacterAttributes()).thenReturn(optimal);
        return animal;
    }

    private KeeperUtils givenKeeperUtilsWithOccupations(TimedOccupation occ1, AnimalKeeper keeper1, Paddock pad1,
            TimedOccupation occ2, AnimalKeeper keeper2, Paddock pad2) {
        KeeperUtils utils = Mockito.mock(KeeperUtils.class);
        Mockito.when(utils.listOfTimedOccupationsInGivenPaddock(keeper1, pad1)).thenReturn(Arrays.asList(occ1));
        Mockito.when(utils.listOfTimedOccupationsInGivenPaddock(keeper2, pad2)).thenReturn(Arrays.asList(occ2));
        return utils;
    }

    private AnimalKeeper givenKeeperWithCompetence(double competence) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, competence);
        Mockito.when(keeper.getFamilyEvaluations()).thenReturn(map);
        return keeper;
    }

    private AnimalEvaluationContext givenContext(List<AnimalKeeper> keepers, Animal animal, Paddock pad) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getFamily()).thenReturn(Family.LEMURIDAE);
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getPaddock()).thenReturn(pad);
        Mockito.when(context.getBase()).thenCallRealMethod();
        AnimalWellBeing wb = Mockito.mock(AnimalWellBeing.class);
        Mockito.when(wb.getKeepersTimeWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setKeepersTimeWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryGreaterThanOneHalfAndOnlyOneKeeperInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.7);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad2);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo(0.3 * 5 * 0.5);
    }
    
     @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryEqualToOneHalfAndOnlyOneKeeperInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.5);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad2);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo(0.3 * 5 * 0.5);
    }
    
     @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryLowerThanOneHalfAndOnlyOneKeeperInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.1);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad2);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo(- 0.3 * 5 * 0.5);
    }
    
     @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryGreaterThanOneHalfAndTwoKeepersInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.7);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad1);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo((0.3 * 0.5 + 0.4 * 0.4) * 5 );
    }
    
    @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryEqualsToOneHalfAndTwoKeepersInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.5);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad1);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo((0.3 * 0.5 + 0.4 * 0.4) * 5 );
    }
    
    @Test
    public void shouldSetTheKeepersTimeWBToBaseTimesTimeWhenTheAnimalHasABraveryLowerThanOneHalfAndTwoKeepersInThePaddock() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithBravery(0.3);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time1)
                .build();
        double time2 = 0.4;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad1)
                .time(time2)
                .build();
        AnimalKeeper keeper1 = givenKeeperWithCompetence(0.5);
        AnimalKeeper keeper2 = givenKeeperWithCompetence(0.4);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(occ1, keeper1, pad1, occ2, keeper2, pad1);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalKeepersTimeInfluenceEvaluationController controller = new AnimalKeepersTimeInfluenceEvaluationController(utils);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTimeWellBeing()).isEqualTo(- (0.3 * 0.5 + 0.4 * 0.4) * 5 );

    }
}
