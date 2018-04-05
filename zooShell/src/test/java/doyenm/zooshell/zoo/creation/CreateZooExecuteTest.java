package doyenm.zooshell.zoo.creation;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author doyenm
 */
public class CreateZooExecuteTest {

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        ZooCreationValidator creationValidator = givenCreationValidator(true);
        ZooCreationController controller = givenController();
        CreateZoo command = new CreateZoo(creationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheValidatorReturnsFalse() {
        // Given
        ZooCreationValidator creationValidator = givenCreationValidator(false);
        ZooCreationController controller = givenController();
        CreateZoo command = new CreateZoo(creationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
    }

    private ZooCreationValidator givenCreationValidator(boolean isOk) {
        ZooCreationValidator validator = mock(ZooCreationValidator.class);
        when(validator.apply(any(ZooCreationContext.class))).thenAnswer(new Answer<ZooCreationContext>() {
            @Override
            public ZooCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                if (!isOk) {
                    ZooCreationContext context = (ZooCreationContext) args[0];
                    context.getErrors().add(BusinessError.builder().type(TestUtils.getErrorType()).build());
                    return context;
                } else {
                    return (ZooCreationContext) args[0];
                }
            }
        });
        return validator;
    }

    private ZooCreationController givenController() {
        ZooCreationController controller = mock(ZooCreationController.class);
        when(controller.apply(any(ZooCreationContext.class))).thenAnswer(new Answer<ZooCreationContext>() {
            @Override
            public ZooCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooCreationContext) args[0];
            }
        });
        return controller;
    }


}
