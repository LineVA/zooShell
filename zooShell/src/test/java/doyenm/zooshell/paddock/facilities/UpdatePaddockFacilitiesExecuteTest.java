package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;
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
public class UpdatePaddockFacilitiesExecuteTest {

    private UpdatePaddockFacilities subject;

    private UpdatePaddocFacilityExistenceValidator existenceValidator;
    private UpdatePaddockFacilityCoherenceValidator coherenceValidator;
    private UpdatePaddockFacilityController controller;

    private UpdatePaddockFacilityContext contextWithNoError;
    private UpdatePaddockFacilityContext contextWithError;

    private String[] cmd = new String[4];


    @Before
    public void setUp(){
        existenceValidator = mock(UpdatePaddocFacilityExistenceValidator.class);
        coherenceValidator = mock(UpdatePaddockFacilityCoherenceValidator.class);
        controller = mock(UpdatePaddockFacilityController.class);
        when(controller.apply(any(UpdatePaddockFacilityContext.class))).thenAnswer(new Answer<UpdatePaddockFacilityContext>() {
            @Override
            public UpdatePaddockFacilityContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (UpdatePaddockFacilityContext) args[0];
            }
        });

        contextWithNoError = mock(UpdatePaddockFacilityContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(UpdatePaddockFacilityContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new UpdatePaddockFacilities(existenceValidator, coherenceValidator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(existenceValidator).apply(any());
        doReturn(contextWithNoError).when(coherenceValidator).apply(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheExistenceValidatorReturnsFalse() {
        // Given
        doReturn(contextWithError).when(existenceValidator).apply(any());
        doReturn(existenceValidator.apply(any())).when(coherenceValidator).apply(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheCoherenceValidatorReturnsFalse() {
        // Given
        doReturn(contextWithNoError).when(existenceValidator).apply(any());
        doReturn(contextWithError).when(coherenceValidator).apply(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }
}
