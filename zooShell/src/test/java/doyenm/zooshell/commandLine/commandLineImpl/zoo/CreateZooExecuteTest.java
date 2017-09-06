package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.commandImpl.zoo.CreateZoo;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import doyenm.zooshell.validator.ZooCreationValidator;
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