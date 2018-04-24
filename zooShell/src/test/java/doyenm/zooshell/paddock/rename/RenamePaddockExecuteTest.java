package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.PaddockContext;
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
 *
 * @author doyenm
 */
public class RenamePaddockExecuteTest {

    private RenamePaddock subject;

    private PaddockChangeNameValidator validator;
    private PaddockChangeNameController controller;

    String[] cmd = new String[4];

    private PaddockChangeNameContext contextWithNoError;
    private PaddockChangeNameContext contextWithError;

    @Before
    public void setUp(){
        validator = Mockito.mock(PaddockChangeNameValidator.class);

        controller = Mockito.mock(PaddockChangeNameController.class);
        Mockito.when(controller.apply(any(PaddockChangeNameContext.class))).thenAnswer(new Answer<PaddockChangeNameContext>() {
            @Override
            public PaddockChangeNameContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockChangeNameContext) args[0];
            }
        });

        contextWithNoError = mock(PaddockChangeNameContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(PaddockChangeNameContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(ErrorType.INCORRECT_LOCATION)));

        subject = new RenamePaddock(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(validator).apply(any(PaddockChangeNameContext.class));
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }
    
     @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
         doReturn(contextWithError).when(validator).apply(any(PaddockChangeNameContext.class));
         // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNull();
    }
}
