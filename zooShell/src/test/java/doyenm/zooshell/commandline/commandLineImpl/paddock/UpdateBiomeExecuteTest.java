package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.UpdateBiome;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.UpdateBiomeContext;
import doyenm.zooshell.controller.paddockcontroller.UpdateBiomeController;
import doyenm.zooshell.model.Zoo;
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
public class UpdateBiomeExecuteTest {

    private UpdateBiomeValidator givenValidator(boolean value) {
        UpdateBiomeValidator validator = Mockito.mock(UpdateBiomeValidator.class);
        Mockito.doReturn(value).when(validator).test(any(UpdateBiomeContext.class));
        return validator;
    }

    private UpdateBiomeController givenController() {
        UpdateBiomeController controller = Mockito.mock(UpdateBiomeController.class);
        Mockito.when(controller.apply(any(UpdateBiomeContext.class))).thenAnswer(new Answer<UpdateBiomeContext>() {
            @Override
            public UpdateBiomeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (UpdateBiomeContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        UpdateBiomeValidator validator = givenValidator(true);
        UpdateBiomeController controller = givenController();
        UpdateBiome command = new UpdateBiome(validator, controller);
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
        UpdateBiomeValidator validator = givenValidator(false);
        UpdateBiomeController controller = givenController();
        UpdateBiome command = new UpdateBiome(validator, controller);
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
