package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationAgeingControllerApplyTest {
    
    private AnimalKeeper givenKeeper() {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getAge()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(keeper).setAge(Mockito.anyInt());
        return keeper;
    }
    
    private KeeperEvaluationContext givenContextWithKeeperAgeAndMonthsPerTurn(AnimalKeeper keeper, int age, int months) {
        KeeperEvaluationContext context = Mockito.mock(KeeperEvaluationContext.class);
        Mockito.when(context.getKeeper()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setKeeper(Mockito.any(AnimalKeeper.class));
        context.setKeeper(keeper);
        Mockito.when(context.getMonthsPerTurn()).thenReturn(months);
        Mockito.when(context.getKeeperAge()).thenReturn(age);
        return context;
    }
    
    @Test
    public void shouldTheKeeperWinTheSameNumberOfMonthsThanTheNupmberOfMonthsPerTurn() {
        // Given
        int monthsPerTurn = TestUtils.generateInteger();
        int age = TestUtils.generateInteger();
        AnimalKeeper keeper = givenKeeper();
        KeeperEvaluationContext context = givenContextWithKeeperAgeAndMonthsPerTurn(keeper, age, monthsPerTurn);
        KeeperEvaluationAgeingController controller = new KeeperEvaluationAgeingController();
        // When
        KeeperEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getKeeper()).isNotNull();
        Assertions.assertThat(actualContext.getKeeper().getAge()).isEqualTo(age + monthsPerTurn);
    }
}
