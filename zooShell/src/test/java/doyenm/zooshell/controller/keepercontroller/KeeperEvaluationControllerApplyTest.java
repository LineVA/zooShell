package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationControllerApplyTest {

    private KeeperEvaluationAgeingController givenAgeingController() {
        KeeperEvaluationAgeingController ctrl = Mockito.mock(KeeperEvaluationAgeingController.class);
        Mockito.when(ctrl.apply(Mockito.any(KeeperEvaluationContext.class)))
                .thenAnswer(new Answer<KeeperEvaluationContext>() {
                    public KeeperEvaluationContext answer(InvocationOnMock invocation) {
                        return (KeeperEvaluationContext) invocation.getArguments()[0];
                    }
                });
        return ctrl;
    }

    private KeeperEvaluationTaskController givenTaskController() {
        KeeperEvaluationTaskController ctrl = Mockito.mock(KeeperEvaluationTaskController.class);
        Mockito.when(ctrl.apply(Mockito.any(KeeperEvaluationContext.class)))
                .thenAnswer(new Answer<KeeperEvaluationContext>() {
                    public KeeperEvaluationContext answer(InvocationOnMock invocation) {
                        return (KeeperEvaluationContext) invocation.getArguments()[0];
                    }
                });
        return ctrl;
    }

    private KeeperEvaluationFamilyController givenFamilyController() {
        KeeperEvaluationFamilyController ctrl = Mockito.mock(KeeperEvaluationFamilyController.class);
        Mockito.when(ctrl.apply(Mockito.any(KeeperEvaluationContext.class)))
                .thenAnswer(new Answer<KeeperEvaluationContext>() {
                    public KeeperEvaluationContext answer(InvocationOnMock invocation) {
                        return (KeeperEvaluationContext) invocation.getArguments()[0];
                    }
                });
        return ctrl;
    }

    private EvaluationContext givenContextWithKeepers(Map<String, AnimalKeeper> keepers) {
        EvaluationContext context = Mockito.mock(EvaluationContext.class);
        Mockito.when(context.getEvaluatedKeepersList()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setEvaluatedKeepersList(Mockito.anyList());
        Mockito.when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        return context;
    }

    @Test
    public void shouldReturnAContextWithTwoElementsInEvaluatedKeepersListWhenThereIsTwoKeepersInTheZoo() {
        // Given
        KeeperEvaluationAgeingController ageingController = givenAgeingController();
        KeeperEvaluationTaskController taskController = givenTaskController();
        KeeperEvaluationFamilyController familyController = givenFamilyController();
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(RandomStringUtils.random(10), Mockito.mock(AnimalKeeper.class));
        keepers.put(RandomStringUtils.random(10), Mockito.mock(AnimalKeeper.class));
        EvaluationContext context = givenContextWithKeepers(keepers);
        KeeperEvaluationController controller = new KeeperEvaluationController(ageingController, taskController, familyController);
        // When
        EvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext).isNotNull();
        Assertions.assertThat(actualContext.getEvaluatedKeepersList()).isNotNull();
        Assertions.assertThat(actualContext.getEvaluatedKeepersList().size()).isEqualTo(2);

    }

}
