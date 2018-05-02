package doyenm.zooshell.paddock.types;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
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
public class UpdatePaddockTypeExecuteTest {

    private UpdatePaddockType subject;

    private UpdatePaddockTypeValidator validator;
    private UpdatePaddockTypeController controller;

    private UpdatePaddockTypeContext contextWithNoError;
    private UpdatePaddockTypeContext contextWithError;

    String[] cmd = new String[4];

    @Before
    public void setUp(){
        validator = mock(UpdatePaddockTypeValidator.class);

        controller = mock(UpdatePaddockTypeController.class);
        when(controller.apply(any(UpdatePaddockTypeContext.class)))
                .thenAnswer(new Answer<UpdatePaddockTypeContext>() {
                    @Override
                    public UpdatePaddockTypeContext answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (UpdatePaddockTypeContext) args[0];
                    }
                });

        contextWithNoError = mock(UpdatePaddockTypeContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(UpdatePaddockTypeContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(TestUtils.getErrorType())));

        subject = new UpdatePaddockType(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        doReturn(contextWithNoError).when(validator).apply(any(UpdatePaddockTypeContext.class));
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
    
     @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
         doReturn(contextWithError).when(validator).apply(any(UpdatePaddockTypeContext.class));
         // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
