package doyenm.zooshell.commandLine.commandLineImpl.handyman;

import doyenm.zooshell.commandLine.commandImpl.handyman.RemoveHandyman;
import doyenm.zooshell.commandLine.commandImpl.paddock.RemovePaddock;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.controller.handymancontroller.RemovingController;
import doyenm.zooshell.controller.paddockcontroller.PaddockRemoveController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.HandymanValidator;
import doyenm.zooshell.validator.PaddockRemoveValidator;
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
public class RemoveHandymanExecuteTest {

    private HandymanValidator givenValidator(boolean value) {
        HandymanValidator validator = Mockito.mock(HandymanValidator.class);
        Mockito.doReturn(value).when(validator).test(any(HandymanContext.class));
        return validator;
    }

    private RemovingController givenController() {
        RemovingController controller = Mockito.mock(RemovingController.class);
        Mockito.when(controller.apply(any(HandymanContext.class))).thenAnswer(new Answer<HandymanContext>() {
            @Override
            public HandymanContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (HandymanContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        HandymanValidator validator = givenValidator(true);
        RemovingController controller = givenController();
        RemoveHandyman command = new RemoveHandyman(validator, controller);
        String[] cmd = new String[3];
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
        HandymanValidator validator = givenValidator(false);
        RemovingController controller = givenController();
        RemoveHandyman command = new RemoveHandyman(validator, controller);
        String[] cmd = new String[3];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
