package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Training;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyMap;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationTrainingControllerApplyTest {

    private AnimalKeeper givenKeeperWithFamilyMapAndTraining(Map<Family, Double> map, Training training) {
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        
        when(keeper.getFamilyEvaluations()).thenCallRealMethod();
        doCallRealMethod().when(keeper).setFamilyEvaluations(anyMap());
        keeper.setFamilyEvaluations(map);
        
        when(keeper.getTraining()).thenCallRealMethod();
        doCallRealMethod().when(keeper).setTraining(any(Training.class));
        keeper.setTraining(training);
        return keeper;
    }

    private Training givenTraining(Family family, int turns, double bonus) {
        Training mock = mock(Training.class);
        when(mock.getFamilySubject()).thenCallRealMethod();
        doCallRealMethod().when(mock).setFamilySubject(any(Family.class));
        mock.setFamilySubject(family);

        when(mock.getRemainingTurns()).thenCallRealMethod();
        doCallRealMethod().when(mock).setRemainingTurns(anyInt());
        mock.setRemainingTurns(turns);

        when(mock.getBonus()).thenCallRealMethod();
        doCallRealMethod().when(mock).setBonus(anyDouble());
        mock.setBonus(bonus);
        return mock;
    }

    private KeeperEvaluationContext givenContext(AnimalKeeper keeper) {
        KeeperEvaluationContext context = Mockito.mock(KeeperEvaluationContext.class);
        Mockito.when(context.getKeeper()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setKeeper(Mockito.any(AnimalKeeper.class));
        context.setKeeper(keeper);
        return context;
    }

    @Test
    public void shouldChangeNothingWhenTheKeeperIsNotInTraining() {
        // Given
        Map<Family, Double> map = new HashMap<>();
        AnimalKeeper keeper = givenKeeperWithFamilyMapAndTraining(map, null);
        KeeperEvaluationContext context = givenContext(keeper);
        KeeperEvaluationTrainingController controller = new KeeperEvaluationTrainingController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNull();
        Assertions.assertThat(actualContext.getKeeper().getTraining()).isNull();
    }

    @Test
    public void shouldUpdateTheNumberOfRemainingTurnsWhenItIsNotTheLastTurn() {
        // Given
        Map<Family, Double> map = new HashMap<>();
        Training training = givenTraining(Family.LEMURIDAE, 3, RandomUtils.nextInt());
        AnimalKeeper keeper = givenKeeperWithFamilyMapAndTraining(map, training);
        KeeperEvaluationContext context = givenContext(keeper);
        KeeperEvaluationTrainingController controller = new KeeperEvaluationTrainingController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNull();
        Assertions.assertThat(actualContext.getKeeper().getTraining()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getTraining().getRemainingTurns()).isEqualTo(2);
    }

    @Test
    public void shouldUpdateTheTrainingAndTheFamilyEvaluationsWhenItWasTheLastTurnAndTheKeeperNeverWorkedWithTheFamily() {
        // Given
        Map<Family, Double> map = new HashMap<>();
        Training training = givenTraining(Family.LEMURIDAE, 1, RandomUtils.nextInt());
        AnimalKeeper keeper = givenKeeperWithFamilyMapAndTraining(map, training);
        KeeperEvaluationContext context = givenContext(keeper);
        KeeperEvaluationTrainingController controller = new KeeperEvaluationTrainingController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isEqualTo(training.getBonus());
        Assertions.assertThat(actualContext.getKeeper().getTraining()).isNull();
    }
    
     @Test
    public void shouldUpdateTheTrainingAndTheFamilyEvaluationsWhenItWasTheLastTurnAndTheKeeperHasAlreadyWorkedWithTheFamily() {
        // Given
        Map<Family, Double> map = new HashMap<>();
        map.put(Family.LEMURIDAE, 0.3);
        Training training = givenTraining(Family.LEMURIDAE, 1,0.1);
        AnimalKeeper keeper = givenKeeperWithFamilyMapAndTraining(map, training);
        KeeperEvaluationContext context = givenContext(keeper);
        KeeperEvaluationTrainingController controller = new KeeperEvaluationTrainingController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE)).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getFamilyEvaluations().get(Family.LEMURIDAE))
                .isEqualTo(0.4);
        Assertions.assertThat(actualContext.getKeeper().getTraining()).isNull();
    }

}
