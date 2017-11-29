package doyenm.zooshell.commandLine.commandLineImpl;

import doyenm.zooshell.backup.LoadFunction;
import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandLine.commandImpl.Load;
import doyenm.zooshell.commandLine.commandImpl.Save;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author doyenm
 */
public class LoadExecuteTest {

    private LoadFunction givenController_1() {
        LoadFunction controller = Mockito.mock(LoadFunction.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Zoo zoo = Zoo.builder().build();
                ZooContext context = (ZooContext) args[0];
                context.setZoo(zoo);
                return context;
            }
        });
        return controller;
    }
    
      private LoadFunction givenController_2() {
        LoadFunction controller = Mockito.mock(LoadFunction.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenReturn(null);
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        LoadFunction controller = givenController_1();
        Load command = new Load(controller);
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
        LoadFunction controller = givenController_2();
        Load command = new Load(controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
