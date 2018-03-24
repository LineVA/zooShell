package doyenm.zooshell.commandline.commandLineImpl;

import doyenm.zooshell.commandline.commandimpl.Evaluate;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.EvaluationController;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author doyenm
 */
public class EvaluateExecuteTest {

    private EvaluationController givenController() {
        EvaluationController controller = Mockito.mock(EvaluationController.class);
        Mockito.when(controller.apply(any(EvaluationContext.class))).thenAnswer(new Answer<EvaluationContext>() {
            @Override
            public EvaluationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (EvaluationContext) args[0];
            }
        });
        return controller;
    }

//    @Test
//    public void shouldReturnAReturnExecWithTypeReturnToSuccess() {
//        // Given
//        EvaluationController controller = givenController();
//        Evaluate command = new Evaluate(controller);
//        String[] cmd = new String[1];
//        // When
//        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
//        // Then
//        Assertions.assertThat(result).isNotNull();
//        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
//        Assertions.assertThat(result.getMessage()).isNotNull();
//    }
}
