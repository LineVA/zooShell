package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
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
public class UpdateBiomeExecuteTest {

    private UpdateBiome subject;

    private UpdateBiomeValidator validator;
    private UpdateBiomeController controller;

    private UpdateBiomeContext contextWithNoError;
    private UpdateBiomeContext contextWithError;

    private String[] cmd = new String[4];

    @Before
    public void setUp(){
        validator = mock(UpdateBiomeValidator.class);

        controller = mock(UpdateBiomeController.class);
        when(controller.apply(any(UpdateBiomeContext.class))).thenAnswer(new Answer<UpdateBiomeContext>() {
            @Override
            public UpdateBiomeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (UpdateBiomeContext) args[0];
            }
        });

        contextWithNoError = mock(UpdateBiomeContext.class);
        when(contextWithNoError.getErrors()).thenReturn(new ArrayList<>());

        contextWithError = mock(UpdateBiomeContext.class);
        when(contextWithError.getErrors()).thenReturn(Arrays.asList(new BusinessError(TestUtils.getErrorType())));

        subject = new UpdateBiome(validator, controller);
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
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
         doReturn(contextWithError).when(validator).apply(any(UpdateBiomeContext.class));
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNull();
    }
}
