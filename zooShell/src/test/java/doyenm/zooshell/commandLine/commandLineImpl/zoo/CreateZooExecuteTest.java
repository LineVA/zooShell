package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.paddock.DetailPad;
import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.ZooDetailsContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockDetailsController;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
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
public class DetailZooExecuteTest {

    private ZooDetailsController givenController() {
        ZooDetailsController controller = Mockito.mock(ZooDetailsController.class);
        Mockito.when(controller.apply(any(ZooDetailsContext.class))).thenAnswer(new Answer<ZooDetailsContext>() {
            @Override
            public ZooDetailsContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooDetailsContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        ZooDetailsController controller = givenController();
        DetailZoo command = new DetailZoo(controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
