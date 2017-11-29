package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddockEntry;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockEntryCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockEntryCreationValidator;
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
public class CreatePaddockEntryExecuteTest {

    private PaddockEntryCreationValidator givenCreationValidator(boolean value) {
        PaddockEntryCreationValidator validator = Mockito.mock(PaddockEntryCreationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockEntryCreationContext.class));
        return validator;
    }

    private PaddockEntryCreationController givenController() {
        PaddockEntryCreationController controller = Mockito.mock(PaddockEntryCreationController.class);
        Mockito.when(controller.apply(any(PaddockEntryCreationContext.class))).thenAnswer(new Answer<PaddockEntryCreationContext>() {
            @Override
            public PaddockEntryCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockEntryCreationContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockEntryCreationValidator creationValidator = givenCreationValidator(true);
        PaddockEntryCreationController controller = givenController();
        CreatePaddockEntry command = new CreatePaddockEntry(creationValidator, controller);
        String[] cmd = new String[5];
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
        PaddockEntryCreationValidator creationValidator = givenCreationValidator(false);
        PaddockEntryCreationController controller = givenController();
        CreatePaddockEntry command = new CreatePaddockEntry(creationValidator, controller);
        String[] cmd = new String[5];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }

}
