package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author doyenm
 */
public class CreatePaddockExecuteTest {

    private CreatePaddock subject;
    private PaddockCreationValidator creationValidator;
    private PaddockLocationValidator locationValidator;
    private PaddockCreationController controller;

    private PaddockCreationContext contextWithNoError;
    private PaddockCreationContext contextWithError;

    @Before
    public void setUp() {

        creationValidator = mock(PaddockCreationValidator.class);

        locationValidator = mock(PaddockLocationValidator.class);

        controller = mock(PaddockCreationController.class);
        when(controller.apply(any(PaddockCreationContext.class))).thenAnswer(new Answer<PaddockCreationContext>() {
            @Override
            public PaddockCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockCreationContext) args[0];
            }
        });

        contextWithNoError = mock(PaddockCreationContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(PaddockCreationContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new CreatePaddock(creationValidator, locationValidator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(creationValidator).apply(any(PaddockCreationContext.class));
        doReturn(contextWithNoError).when(locationValidator).apply(any(PaddockCreationContext.class));
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheCreationValidatorReturnsErrors() {
        // Given
        doReturn(contextWithError).when(creationValidator).apply(any(PaddockCreationContext.class));
        PaddockCreationContext contextReturnedByCreationValidator = creationValidator.apply(any());
        doReturn(contextReturnedByCreationValidator).when(locationValidator).apply(any(PaddockCreationContext.class));
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheLocationValidatorReturnsErrors() {
        // Given
        doReturn(contextWithNoError).when(creationValidator).apply(any(PaddockCreationContext.class));
        doReturn(contextWithError).when(locationValidator).apply(any(PaddockCreationContext.class));
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }

}
