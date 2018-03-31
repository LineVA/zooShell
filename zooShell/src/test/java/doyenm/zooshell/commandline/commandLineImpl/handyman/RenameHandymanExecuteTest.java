package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.handyman.rename.RenameHandyman;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.handyman.rename.HandymanRenameContext;
import doyenm.zooshell.handyman.rename.RenamingController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.handyman.rename.HandymanRenameValidator;
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
