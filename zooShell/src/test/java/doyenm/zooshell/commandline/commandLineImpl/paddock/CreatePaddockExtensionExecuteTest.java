package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.CreatePaddockExtension;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockExtensionCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
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
public class CreatePaddockExtensionExecuteTest {

    private PaddockExtensionLocationValidator givenLocationValidator(boolean value) {
        PaddockExtensionLocationValidator validator = Mockito.mock(PaddockExtensionLocationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockExtensionCreationContext.class));
        return validator;
    }

    private PaddockExtensionCreationValidator givenCreationValidator(boolean value) {
        PaddockExtensionCreationValidator validator = Mockito.mock(PaddockExtensionCreationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockExtensionCreationContext.class));
        return validator;
    }

    private PaddockExtensionCreationController givenController() {
        PaddockExtensionCreationController controller = Mockito.mock(PaddockExtensionCreationController.class);
        Mockito.when(controller.apply(any(PaddockExtensionCreationContext.class))).thenAnswer(new Answer<PaddockExtensionCreationContext>() {
            @Override
            public PaddockExtensionCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockExtensionCreationContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockExtensionCreationValidator creationValidator = givenCreationValidator(true);
        PaddockExtensionLocationValidator locationValidator = givenLocationValidator(true);
        PaddockExtensionCreationController controller = givenController();
        CreatePaddockExtension command = new CreatePaddockExtension(creationValidator, locationValidator, controller);
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
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCreationValidatorReturnsFalse() {
        // Given
        PaddockExtensionCreationValidator creationValidator = givenCreationValidator(false);
        PaddockExtensionLocationValidator locationValidator = givenLocationValidator(true);
        PaddockExtensionCreationController controller = givenController();
        CreatePaddockExtension command = new CreatePaddockExtension(creationValidator, locationValidator, controller);
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
        PaddockExtensionCreationValidator creationValidator = givenCreationValidator(true);
        PaddockExtensionLocationValidator locationValidator = givenLocationValidator(false);
        PaddockExtensionCreationController controller = givenController();
        CreatePaddockExtension command = new CreatePaddockExtension(creationValidator, locationValidator, controller);
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
