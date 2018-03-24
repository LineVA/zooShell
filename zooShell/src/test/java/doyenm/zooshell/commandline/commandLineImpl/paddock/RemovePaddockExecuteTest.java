package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.RemovePaddock;
import doyenm.zooshell.commandline.commandimpl.paddock.UpdateBiome;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockRemoveController;
import doyenm.zooshell.controller.paddockcontroller.UpdateBiomeController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockRemoveValidator;
import doyenm.zooshell.validator.UpdateBiomeValidator;
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
public class RemovePaddockExecuteTest {

    private PaddockRemoveValidator givenValidator(boolean value) {
        PaddockRemoveValidator validator = Mockito.mock(PaddockRemoveValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockContext.class));
        return validator;
    }

    private PaddockRemoveController givenController() {
        PaddockRemoveController controller = Mockito.mock(PaddockRemoveController.class);
        Mockito.when(controller.apply(any(PaddockContext.class))).thenAnswer(new Answer<PaddockContext>() {
            @Override
            public PaddockContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockRemoveValidator validator = givenValidator(true);
        PaddockRemoveController controller = givenController();
        RemovePaddock command = new RemovePaddock(validator, controller);
        String[] cmd = new String[4];
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
        PaddockRemoveValidator validator = givenValidator(false);
        PaddockRemoveController controller = givenController();
        RemovePaddock command = new RemovePaddock(validator, controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
