package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.paddock.rename.RenamePaddock;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.rename.PaddockChangeNameContext;
import doyenm.zooshell.paddock.rename.PaddockChangeNameController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.rename.PaddockChangeNameValidator;
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
public class RenamePaddockExecuteTest {

    private PaddockChangeNameValidator givenValidator(boolean value) {
        PaddockChangeNameValidator validator = Mockito.mock(PaddockChangeNameValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockChangeNameContext.class));
        return validator;
    }

    private PaddockChangeNameController givenController() {
        PaddockChangeNameController controller = Mockito.mock(PaddockChangeNameController.class);
        Mockito.when(controller.apply(any(PaddockChangeNameContext.class))).thenAnswer(new Answer<PaddockChangeNameContext>() {
            @Override
            public PaddockChangeNameContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockChangeNameContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockChangeNameValidator validator = givenValidator(true);
        PaddockChangeNameController controller = givenController();
        RenamePaddock command = new RenamePaddock(validator, controller);
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
        PaddockChangeNameValidator validator = givenValidator(false);
        PaddockChangeNameController controller = givenController();
        RenamePaddock command = new RenamePaddock(validator, controller);
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
