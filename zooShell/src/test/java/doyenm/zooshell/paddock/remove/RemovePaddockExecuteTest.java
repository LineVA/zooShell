package doyenm.zooshell.paddock.remove;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.paddock.extension.*;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.PaddockContext;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class RemovePaddockExecuteTest {

    private RemovePaddock subject;

    private PaddockRemoveValidator validator;
    private PaddockRemoveController controller;

    private PaddockContext contextWithNoError;
    private PaddockContext contextWithError;

    String[] cmd = new String[4];

    @Before
    public void setUp(){
        validator = mock(PaddockRemoveValidator.class);
        controller = mock(PaddockRemoveController.class);
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

        subject = new RemovePaddock(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        when(validator.apply(any())).thenReturn(contextWithNoError);
        // When
        ReturnExec result = subject.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }
    
     @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
         when(validator.apply(any())).thenReturn(contextWithError);
         // When
        ReturnExec result = subject.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNull();
    }
}
