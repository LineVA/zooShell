package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.creation.PaddockCreationContext;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class CreatePaddockEntryExecuteTest {

    private CreatePaddockEntry subject;

    private PaddockEntryCreationValidator creationValidator;
    private PaddockEntryCreationController controller;

    private PaddockEntryCreationContext contextWithNoError;
    private PaddockEntryCreationContext contextWithError;

    @Before
    public void setUp(){
        creationValidator = mock(PaddockEntryCreationValidator.class);

        controller = mock(PaddockEntryCreationController.class);
        when(controller.apply(any(PaddockEntryCreationContext.class))).thenAnswer(new Answer<PaddockEntryCreationContext>() {
            @Override
            public PaddockEntryCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockEntryCreationContext) args[0];
            }
        });

        contextWithNoError = mock(PaddockEntryCreationContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(PaddockEntryCreationContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

       subject = new CreatePaddockEntry(creationValidator, controller);
    }


    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(creationValidator).apply(any(PaddockEntryCreationContext.class));
        String[] cmd = new String[5];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheCCreationValidatorReturnsFalse() {
        // Given
        doReturn(contextWithError).when(creationValidator).apply(any(PaddockEntryCreationContext.class));
        String[] cmd = new String[5];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }

}
