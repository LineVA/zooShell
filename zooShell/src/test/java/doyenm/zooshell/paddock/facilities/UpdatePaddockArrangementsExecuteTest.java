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
public class UpdatePaddockArrangementsExecuteTest {

    private UpdatePaddockArrangements subject;

    private UpdatePaddockArrangementValidator validator;
    private UpdatePaddockArrangementController controller;

    private UpdatePaddockArrangementContext contextWithNoError;
    private UpdatePaddockArrangementContext contextWithError;

    private String[] cmd = new String[4];


    @Before
    public void setUp(){
        validator = mock(UpdatePaddockArrangementValidator.class);
        controller = mock(UpdatePaddockArrangementController.class);
        when(controller.apply(any(UpdatePaddockArrangementContext.class))).thenAnswer(new Answer<UpdatePaddockArrangementContext>() {
            @Override
            public UpdatePaddockArrangementContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (UpdatePaddockArrangementContext) args[0];
            }
        });

        contextWithNoError = mock(UpdatePaddockArrangementContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(UpdatePaddockArrangementContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new UpdatePaddockArrangements(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(validator).apply(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToErrorWhenTheValidatorReturnsFalse() {
        // Given
        doReturn(contextWithError).when(validator).apply(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }
}
