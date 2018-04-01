package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.paddock.details.DetailPad;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.paddock.details.PaddockDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.PaddockValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;

/**
 *
 * @author doyenm
 */
public class DetailPadExecuteTest {

    private PaddockValidator givenValidator(boolean value) {
        PaddockValidator validator = Mockito.mock(PaddockValidator.class);
        Mockito.doReturn(value).when(validator).test(any(PaddockContext.class));
        return validator;
    }

    private PaddockDetailsController givenController() {
        PaddockDetailsController controller = Mockito.mock(PaddockDetailsController.class);
        Mockito.when(controller.apply(any(PaddockContext.class))).thenAnswer(new Answer<PaddockContext>() {
            @Override
            public PaddockContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (PaddockContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        PaddockValidator validator = givenValidator(true);
        PaddockDetailsController controller = givenController();
        DetailPad command = new DetailPad(validator, controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNull();
    }
    
     @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
        PaddockValidator validator = givenValidator(false);
        PaddockDetailsController controller = givenController();
        DetailPad command = new DetailPad(validator, controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNull();
    }
}
