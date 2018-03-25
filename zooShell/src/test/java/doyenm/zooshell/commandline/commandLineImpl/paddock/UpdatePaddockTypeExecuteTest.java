package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.UpdatePaddockType;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.UpdatePaddockTypeContext;
import doyenm.zooshell.controller.paddockcontroller.UpdatePaddockTypeController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.UpdatePaddockTypeValidator;
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
public class UpdatePaddockTypeExecuteTest {

    private UpdatePaddockTypeValidator givenValidator(boolean value) {
        UpdatePaddockTypeValidator validator = Mockito.mock(UpdatePaddockTypeValidator.class);
        Mockito.doReturn(value).when(validator).test(any(UpdatePaddockTypeContext.class));
        return validator;
    }

    private UpdatePaddockTypeController givenController() {
        UpdatePaddockTypeController controller = Mockito.mock(UpdatePaddockTypeController.class);
        Mockito.when(controller.apply(any(UpdatePaddockTypeContext.class)))
                .thenAnswer(new Answer<UpdatePaddockTypeContext>() {
            @Override
            public UpdatePaddockTypeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (UpdatePaddockTypeContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        UpdatePaddockTypeValidator validator = givenValidator(true);
        UpdatePaddockTypeController controller = givenController();
        UpdatePaddockType command = new UpdatePaddockType(validator, controller);
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
        UpdatePaddockTypeValidator validator = givenValidator(false);
        UpdatePaddockTypeController controller = givenController();
        UpdatePaddockType command = new UpdatePaddockType(validator, controller);
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
