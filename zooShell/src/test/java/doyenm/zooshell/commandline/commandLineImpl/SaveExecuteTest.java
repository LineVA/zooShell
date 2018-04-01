package doyenm.zooshell.commandline.commandLineImpl;

import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.backup.Save;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;

/**
 *
 * @author doyenm
 */
public class SaveExecuteTest {

    private SaveFunction givenController_1() {
        SaveFunction controller = Mockito.mock(SaveFunction.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooContext) args[0];
            }
        });
        return controller;
    }
    
      private SaveFunction givenController_2() {
        SaveFunction controller = Mockito.mock(SaveFunction.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenReturn(null);
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        SaveFunction controller = givenController_1();
        Save command = new Save(controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
        SaveFunction controller = givenController_2();
        Save command = new Save(controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
}
