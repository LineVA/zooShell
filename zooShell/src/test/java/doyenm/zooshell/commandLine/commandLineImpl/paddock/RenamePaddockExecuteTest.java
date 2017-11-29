package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.RenamePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.UpdateBiome;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.controller.paddockcontroller.UpdateBiomeController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
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
