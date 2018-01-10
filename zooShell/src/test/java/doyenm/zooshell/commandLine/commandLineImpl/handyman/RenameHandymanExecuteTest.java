package doyenm.zooshell.commandLine.commandLineImpl.handyman;

import doyenm.zooshell.commandLine.commandImpl.handyman.RenameHandyman;
import doyenm.zooshell.commandLine.commandImpl.paddock.RenamePaddock;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.controller.handymancontroller.RenamingController;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.HandymanRenameValidator;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
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
public class RenameHandymanExecuteTest {

    private HandymanRenameValidator givenValidator(boolean value) {
        HandymanRenameValidator validator = Mockito.mock(HandymanRenameValidator.class);
        Mockito.doReturn(value).when(validator).test(any(HandymanRenameContext.class));
        return validator;
    }

    private RenamingController givenController() {
        RenamingController controller = Mockito.mock(RenamingController.class);
        Mockito.when(controller.apply(any(HandymanRenameContext.class))).thenAnswer(new Answer<HandymanRenameContext>() {
            @Override
            public HandymanRenameContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (HandymanRenameContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        HandymanRenameValidator validator = givenValidator(true);
        RenamingController controller = givenController();
        RenameHandyman command = new RenameHandyman(validator, controller);
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
        HandymanRenameValidator validator = givenValidator(false);
        RenamingController controller = givenController();
        RenameHandyman command = new RenameHandyman(validator, controller);
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
