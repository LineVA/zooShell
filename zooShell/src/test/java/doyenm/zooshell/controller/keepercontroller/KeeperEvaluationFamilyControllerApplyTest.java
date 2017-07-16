package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.offset;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationFamilyControllerApplyTest {

    private AnimalKeeper givenKeeperWithFamilyMap(Map<Family, Double> map) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getFamilyEvaluations()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(keeper).setFamilyEvaluations(Mockito.anyMap());
        keeper.setFamilyEvaluations(map);
        return keeper;
    }

    private KeeperEvaluationContext givenContextWithOccupationsAnimalsAndKeeper(
            List<TimedOccupation> occs, List<Animal> animals, AnimalKeeper keeper) {
        KeeperEvaluationContext context = Mockito.mock(KeeperEvaluationContext.class);
        Mockito.when(context.getKeeper()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setKeeper(Mockito.any(AnimalKeeper.class));
        context.setKeeper(keeper);
        Mockito.when(context.getOccupations()).thenReturn(occs);
        Mockito.when(context.getFamilyEvaluationMap()).thenCallRealMethod();
        Mockito.when(context.getAnimals()).thenReturn(animals);
        return context;
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToZeroWhenItIsInitiallyZeroAndTheKeeperDoesNotPractice() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.MEDICAL_TRAINING)
                .time(time1)
                .build();
        Map<Family, Double> map = new HashMap<>();
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1), new ArrayList<>(), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNull();

    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToXWhenItIsInitiallyZeroAndTheKeeperPracticesForXTime() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal = Animal.builder().specie(specie).paddock(pad1).build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double init = 0.0;
        Map<Family, Double> map = new HashMap<>();
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1), Arrays.asList(animal), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(time1 + init);
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToXWhenItIsInitiallyZeroAndTheKeeperPracticeXTimeInTwoPaddocks() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Paddock pad2 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        Animal animal2 = Animal.builder().specie(specie).paddock(pad2).build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.3;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.CLEANING)
                .time(time2)
                .build();
        double init = 0.0;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1, occ2),
                Arrays.asList(animal1, animal2), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(init + time1 + time2);
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToXWhenItIsInitiallyXAndTheKeeperDoesNotPractice() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.MEDICAL_TRAINING)
                .time(time1)
                .build();
        double init = 0.5;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1), Arrays.asList(animal1), keeper);
        KeeperEvaluationTaskController controller = new KeeperEvaluationTaskController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(init);
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToZWhenItIsInitiallyXAndTheKeeperPracticeYTime() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        double time1 = 0.5;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double init = 0.4;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1), Arrays.asList(animal1), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(init + time1);
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeToZWhenItIsInitiallyXAndTheKeeperPracticeYTimeInTwoPaddocks() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        Paddock pad2 = Paddock.builder().build();
        Animal animal2 = Animal.builder().specie(specie).paddock(pad2).build();
        double time1 = 0.50;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.30;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.CLEANING)
                .time(time2)
                .build();
        double init = 0.10;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1, occ2),
                Arrays.asList(animal1, animal2), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        double result = init + time1 + time2;
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(result, offset(0.01));
    }

    @Test
    public void shoulSetTheCompetenceInLemuridaeAndUnknownToXAndYWhenTheKeeperPractices() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        Paddock pad2 = Paddock.builder().build();
        Specie specie2 = new Specie();
        specie2.setFamily(Family.UNKNOWN);
        Animal animal2 = Animal.builder().specie(specie2).paddock(pad2).build();
        double time1 = 0.50;
        TimedOccupation occ1 = TimedOccupation.builder()
                .paddock(pad1)
                .taskType(TaskType.CLEANING)
                .time(time1)
                .build();
        double time2 = 0.30;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.FEEDING)
                .time(time2)
                .build();
        double init = 0.10;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ1, occ2),
                Arrays.asList(animal1, animal2), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        System.out.println(context.getOccupations());
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(init + time1);
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.UNKNOWN)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.UNKNOWN)).isEqualTo(time2);
    }
    
    @Test
    public void shoulSetTheCompetenceInLemuridaeToZeroWhenTheKeeperHasNeverPracticedWithThisFamilyButInOtherPaddocks() {
        // Given
        Paddock pad1 = Paddock.builder().build();
        Specie specie = new Specie();
        specie.setFamily(Family.LEMURIDAE);
        Animal animal1 = Animal.builder().specie(specie).paddock(pad1).build();
        Paddock pad2 = Paddock.builder().build();
        double time2 = 0.30;
        TimedOccupation occ2 = TimedOccupation.builder()
                .paddock(pad2)
                .taskType(TaskType.FEEDING)
                .time(time2)
                .build();
        double init = 0.0;
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, init);
        AnimalKeeper keeper = givenKeeperWithFamilyMap(map);
        KeeperEvaluationContext context = givenContextWithOccupationsAnimalsAndKeeper(Arrays.asList(occ2),
                Arrays.asList(animal1), keeper);
        KeeperEvaluationFamilyController controller = new KeeperEvaluationFamilyController();
        // When
        System.out.println(context.getOccupations());
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(init);
    }
}
