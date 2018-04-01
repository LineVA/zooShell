package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.paddock.entry.CreatePaddockEntry;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationValidator;
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
