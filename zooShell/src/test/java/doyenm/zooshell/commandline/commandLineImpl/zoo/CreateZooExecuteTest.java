package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.zoo.creation.CreateZoo;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.creation.ZooCreationContext;
import doyenm.zooshell.zoo.creation.ZooCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.zoo.creation.ZooCreationValidator;
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
public class CreateZooExecuteTest {

    private ZooCreationValidator givenCreationValidator(boolean value) {
        ZooCreationValidator validator = Mockito.mock(ZooCreationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(ZooCreationContext.class));
        return validator;
    }

    private ZooCreationController givenController() {
        ZooCreationController controller = Mockito.mock(ZooCreationController.class);
        Mockito.when(controller.apply(any(ZooCreationContext.class))).thenAnswer(new Answer<ZooCreationContext>() {
            @Override
            public ZooCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooCreationContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        ZooCreationValidator creationValidator = givenCreationValidator(true);
        ZooCreationController controller = givenController();
        CreateZoo command = new CreateZoo(creationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
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
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
    }

}
