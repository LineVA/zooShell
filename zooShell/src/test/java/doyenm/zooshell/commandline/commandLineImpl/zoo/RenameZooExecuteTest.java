package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.testUtils.TestUtils;
import doyenm.zooshell.zoo.creation.ZooCreationContext;
import doyenm.zooshell.zoo.creation.ZooCreationValidator;
import doyenm.zooshell.zoo.rename.RenameZoo;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.zoo.rename.RenameZooController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.zoo.rename.RenameZooValidator;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class RenameZooExecuteTest {

    private RenameZoo subject;
    private RenameZooValidator validator;
    private RenameZooController controller;

    @Before
    public void setUp(){
        controller = mock(RenameZooController.class);
        when(controller.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooContext) args[0];
            }
        });

        subject = new RenameZoo(validator, controller);
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        validator = givenValidator(true);
        String[] cmd = new String[3];
        subject = new RenameZoo(validator, controller);
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailureWhenTheCommandFailed() {
        // Given
        validator = givenValidator(false);
        String[] cmd = new String[3];
        subject = new RenameZoo(validator, controller);
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
    }

    private RenameZooValidator givenValidator(boolean isOk) {
        RenameZooValidator validator = mock(RenameZooValidator.class);
        when(validator.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                if (!isOk) {
                    ZooContext context = (ZooContext) args[0];
                    context.getErrors().add(BusinessError.builder().type(TestUtils.getErrorType()).build());
                    return context;
                } else {
                    return (ZooContext) args[0];
                }
            }
        });
        return validator;
    }
}
