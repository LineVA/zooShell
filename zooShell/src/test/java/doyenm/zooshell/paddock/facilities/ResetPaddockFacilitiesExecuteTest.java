package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.paddock.PaddockValidator;
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
public class ResetPaddockFacilitiesExecuteTest {

    private ResetPaddockFacilities subject;

    private PaddockValidator validator;
    private ResetPaddockFacilitiesController controller;

    private PaddockContext contextWithNoError;
    private PaddockContext contextWithError;

    private String[] cmd = new String[4];


    @Before
    public void setUp(){
        validator = mock(PaddockValidator.class);
        controller = mock(ResetPaddockFacilitiesController.class);
        when(controller.apply(any(PaddockContext.class))).thenAnswer(new Answer<PaddockContext>() {
            @Override
            public PaddockContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockContext) args[0];
            }
        });

        contextWithNoError = mock(PaddockContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(PaddockContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new ResetPaddockFacilities(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(true).when(validator).test(any());
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
        doReturn(false).when(validator).test(any());
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }
}
