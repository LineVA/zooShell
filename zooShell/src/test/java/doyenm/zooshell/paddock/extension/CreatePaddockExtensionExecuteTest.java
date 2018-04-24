package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.paddock.creation.CreatePaddock;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;
import doyenm.zooshell.paddock.extension.CreatePaddockExtension;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.extension.PaddockExtensionCreationContext;
import doyenm.zooshell.paddock.extension.PaddockExtensionCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.extension.PaddockExtensionCreationValidator;
import doyenm.zooshell.paddock.extension.PaddockExtensionLocationValidator;
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
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class CreatePaddockExtensionExecuteTest {

    private CreatePaddockExtension subject;

    private PaddockExtensionCreationValidator creationValidator;
    private PaddockExtensionLocationValidator locationValidator;
    private PaddockExtensionCreationController controller;

    private PaddockExtensionCreationContext contextWithNoError;
    private PaddockExtensionCreationContext contextWithError;

    @Before
    public void setUp(){
        creationValidator = mock(PaddockExtensionCreationValidator.class);
        locationValidator = mock(PaddockExtensionLocationValidator.class);
        controller = mock(PaddockExtensionCreationController.class);
        when(controller.apply(any(PaddockExtensionCreationContext.class))).thenAnswer(new Answer<PaddockExtensionCreationContext>() {
            @Override
            public PaddockExtensionCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockExtensionCreationContext) args[0];
            }
        });

        contextWithNoError = mock(PaddockExtensionCreationContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(PaddockExtensionCreationContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new CreatePaddockExtension(creationValidator, locationValidator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(creationValidator).apply(any());
        doReturn(contextWithNoError).when(locationValidator).apply(any());

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
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheCreationValidatorReturnsFalse() {
        // Given
        doReturn(contextWithError).when(creationValidator).apply(any());
        doReturn(creationValidator.apply(any())).when(locationValidator).apply(any());

        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheLocationValidatorReturnsFalse() {
        // Given
        doReturn(contextWithNoError).when(creationValidator).apply(any());
        doReturn(contextWithError).when(locationValidator).apply(any());
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }

}
