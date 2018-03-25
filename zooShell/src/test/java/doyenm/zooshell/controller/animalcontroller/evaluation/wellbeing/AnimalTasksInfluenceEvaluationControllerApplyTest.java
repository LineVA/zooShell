package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.model.*;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class AnimalTasksInfluenceEvaluationControllerApplyTest {

    private CharacterAttributes givenCharacterAttributesWithTrait(
            double curiosity, double gourmandise, double intelligence, double meticulousness) {
        CharacterAttributes attributes = Mockito.mock(CharacterAttributes.class);
        Mockito.when(attributes.getCuriosity()).thenReturn(curiosity);
        Mockito.when(attributes.getGourmandise()).thenReturn(gourmandise);
        Mockito.when(attributes.getInteligence()).thenReturn(intelligence);
        Mockito.when(attributes.getMeticulousness()).thenReturn(meticulousness);

        return attributes;
    }

    private Animal givenAnimalWithCharacterAttributes(CharacterAttributes optimal) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getCharacterAttributes()).thenReturn(optimal);
        return animal;
    }

    private KeeperUtils givenKeeperUtilsWithOccupations(double time1, AnimalKeeper keeper1, Paddock pad1,
            double time2, AnimalKeeper keeper2, Paddock pad2, TaskType task) {
        KeeperUtils utils = Mockito.mock(KeeperUtils.class);
        Mockito.when(utils.timeSpentDoingTheTaskInThePaddock(keeper1, task, pad1)).thenReturn(time1);
        Mockito.when(utils.timeSpentDoingTheTaskInThePaddock(keeper2, task, pad2)).thenReturn(time2);
        return utils;
    }

    private AnimalKeeper givenKeeperWithCompetences(double enrichment, double feeding, double nursing, double training, double cleaning) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Map<TaskType, Double> map = new HashMap<>();
        map.put(TaskType.CLEANING, cleaning);
        map.put(TaskType.ENRICHMENT, enrichment);
        map.put(TaskType.FEEDING, feeding);
        map.put(TaskType.NURSING, nursing);
        map.put(TaskType.MEDICAL_TRAINING, training);
        Mockito.when(keeper.getTaskEvaluations()).thenReturn(map);
        return keeper;
    }

    private AnimalEvaluationContext givenContext(List<AnimalKeeper> keepers, Animal animal, Paddock pad) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getPaddock()).thenReturn(pad);
        Mockito.when(context.getTaskInfluenceWellBeing()).thenCallRealMethod();
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getKeepersTaskWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setKeepersTaskWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    @Test
    public void shouldSetTheTasksWBToBaseTimesTimeWhenTheAnimalHasACuriosityGreaterThanOneHalfAndOnlyOneKeeperInThePaddockDoingEnrichment() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithTrait(0.7, 1.0, 1.0, 1.0);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        AnimalKeeper keeper1 = givenKeeperWithCompetences(0.5, 1.0, 1.0, 1.0, 1.0);
        AnimalKeeper keeper2 = givenKeeperWithCompetences(0.4, 1.0, 1.0, 1.0, 1.0);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(time1, keeper1, pad1, time2, keeper2, pad2, TaskType.ENRICHMENT);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalTasksInfluenceEvaluationController controller = new AnimalTasksInfluenceEvaluationController(utils, 0.5);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTaskWellBeing()).isEqualTo(0.3 * 5 * 0.5 / 4);
    }

    @Test
    public void shouldSetTheTasksWBToBaseTimesTimeWhenTheAnimalHasACuriosityEqualsToOneHalfAndOnlyOneKeeperInThePaddockDoingEnrichment() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithTrait(0.5, 1.0, 1.0, 1.0);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        AnimalKeeper keeper1 = givenKeeperWithCompetences(0.5, 1.0, 1.0, 1.0, 1.0);
        AnimalKeeper keeper2 = givenKeeperWithCompetences(0.4, 1.0, 1.0, 1.0, 1.0);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(time1, keeper1, pad1, time2, keeper2, pad2, TaskType.ENRICHMENT);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalTasksInfluenceEvaluationController controller = new AnimalTasksInfluenceEvaluationController(utils, 0.5);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTaskWellBeing()).isEqualTo(0.3 * 5 * 0.5 / 4);
    }

    @Test
    public void shouldSetTheTasksWBToBaseTimesTimeWhenTheAnimalHasACuriosityLowerThanOneHalfAndOnlyOneKeeperInThePaddockDoingEnrichment() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithTrait(0.3, 1.0, 1.0, 1.0);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        AnimalKeeper keeper1 = givenKeeperWithCompetences(0.5, 1.0, 1.0, 1.0, 1.0);
        AnimalKeeper keeper2 = givenKeeperWithCompetences(0.4, 1.0, 1.0, 1.0, 1.0);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(time1, keeper1, pad1, time2, keeper2, pad2, TaskType.ENRICHMENT);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalTasksInfluenceEvaluationController controller = new AnimalTasksInfluenceEvaluationController(utils, 0.5);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTaskWellBeing()).isEqualTo(-0.3 * 5 * 0.5 / 4);
    }

    @Test
    @Ignore
    public void shouldSetTheTasksWBToBaseTimesTimeWhenTheAnimalHasAIntelligenceThanOneHalfAndOnlyOneKeeperInThePaddockDoingTraining() {
        // Given
        CharacterAttributes characterAttributes = givenCharacterAttributesWithTrait(01.0, 1.0, 0.5, 1.0);
        Animal animal = givenAnimalWithCharacterAttributes(characterAttributes);
        Paddock pad1 = Mockito.mock(Paddock.class);
        double time1 = 0.3;
        Paddock pad2 = Mockito.mock(Paddock.class);
        double time2 = 0.4;
        AnimalKeeper keeper1 = givenKeeperWithCompetences(1.0, 1.0, 1.0, 0.3, 1.0);
        AnimalKeeper keeper2 = givenKeeperWithCompetences(1.0, 1.0, 1.0, 0.4, 1.0);
        KeeperUtils utils = givenKeeperUtilsWithOccupations(time1, keeper1, pad1, time2, keeper2, pad2, TaskType.MEDICAL_TRAINING);
        AnimalEvaluationContext context = givenContext(Arrays.asList(keeper1, keeper2), animal, pad1);
        AnimalTasksInfluenceEvaluationController controller = new AnimalTasksInfluenceEvaluationController(utils, 0.5);
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getWellBeingObj().getKeepersTaskWellBeing()).isEqualTo(0.3 * 5 * 0.5 / 4);
    }
}
