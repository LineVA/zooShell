package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.zoo.rename.RenameZoo;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.zoo.rename.RenameZooController;
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
public class RenameZooExecuteTest {

    private RenameZooController givenController() {
        RenameZooController controller = Mockito.mock(RenameZooController.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        RenameZooController controller = givenController();
        RenameZoo command = new RenameZoo(null, controller);
        String[] cmd = new String[3];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
}
