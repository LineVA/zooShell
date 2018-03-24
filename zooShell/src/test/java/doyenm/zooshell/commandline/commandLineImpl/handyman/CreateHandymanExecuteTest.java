package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.commandline.commandImpl.handyman.CreateHandyman;
import doyenm.zooshell.commandline.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.handymancontroller.CreationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.HandymanCreationValidator;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
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
public class CreateHandymanExecuteTest {


    private HandymanCreationValidator givenCreationValidator(boolean value) {
        HandymanCreationValidator validator = Mockito.mock(HandymanCreationValidator.class);
        Mockito.doReturn(value).when(validator).test(any(HandymanCreationContext.class));
        return validator;
    }

    private CreationController givenController() {
        CreationController controller = Mockito.mock(CreationController.class);
        Mockito.when(controller.apply(any(HandymanCreationContext.class))).thenAnswer(new Answer<HandymanCreationContext>() {
            @Override
            public HandymanCreationContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (HandymanCreationContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        HandymanCreationValidator creationValidator = givenCreationValidator(true);
        CreationController controller = givenController();
        CreateHandyman command = new CreateHandyman(creationValidator, controller);
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
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCCreationValidatorReturnsFalse() {
        // Given
        HandymanCreationValidator creationValidator = givenCreationValidator(false);
        CreationController controller = givenController();
        CreateHandyman command = new CreateHandyman(creationValidator, controller);
        String[] cmd = new String[7];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
    
}
