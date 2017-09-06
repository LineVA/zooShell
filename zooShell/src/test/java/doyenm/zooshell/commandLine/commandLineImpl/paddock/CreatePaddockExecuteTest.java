package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
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
public class CreatePaddockExecuteTest {

    private PaddockLocationValidator givenLocationValidator(boolean value) {
        PaddockLocationValidator validator = Mockito.mock(PaddockLocationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockCreationContext.class));
        return validator;
    }

    private PaddockCreationValidator givenCreationValidator(boolean value) {
        PaddockCreationValidator validator = Mockito.mock(PaddockCreationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockCreationContext.class));
        return validator;
    }

    private PaddockCreationController givenController() {
        PaddockCreationController controller = Mockito.mock(PaddockCreationController.class);
        Mockito.when(controller.apply(any(PaddockCreationContext.class))).thenAnswer(new Answer<PaddockCreationContext>() {
            @Override
            public PaddockCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockCreationContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockCreationValidator creationValidator = givenCreationValidator(true);
        PaddockLocationValidator locationValidator = givenLocationValidator(true);
        PaddockCreationController controller = givenController();
        CreatePaddock command = new CreatePaddock(creationValidator, locationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
    
    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCCreationValidatorReturnsFalse() {
        // Given
        PaddockCreationValidator creationValidator = givenCreationValidator(false);
        PaddockLocationValidator locationValidator = givenLocationValidator(true);
        PaddockCreationController controller = givenController();
        CreatePaddock command = new CreatePaddock(creationValidator, locationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
    
    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheLocationValidatorReturnsFalse() {
        // Given
        PaddockCreationValidator creationValidator = givenCreationValidator(true);
        PaddockLocationValidator locationValidator = givenLocationValidator(false);
        PaddockCreationController controller = givenController();
        CreatePaddock command = new CreatePaddock(creationValidator, locationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

}
