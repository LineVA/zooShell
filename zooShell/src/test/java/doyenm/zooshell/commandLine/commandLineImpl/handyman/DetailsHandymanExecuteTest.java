package doyenm.zooshell.commandLine.commandLineImpl.handyman;

import doyenm.zooshell.commandLine.commandImpl.animal.DetailAnimal;
import doyenm.zooshell.commandLine.commandImpl.handyman.DetailsHandyman;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.controller.handymancontroller.HandymanDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.AnimalValidator;
import doyenm.zooshell.validator.HandymanValidator;
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
public class DetailsHandymanExecuteTest {

    private HandymanValidator givenValidator(boolean value) {
        HandymanValidator validator = Mockito.mock(HandymanValidator.class);
        Mockito.doReturn(value).when(validator).test(any(HandymanContext.class));
        return validator;
    }

    private HandymanDetailsController givenController() {
        HandymanDetailsController controller = Mockito.mock(HandymanDetailsController.class);
        Mockito.when(controller.apply(any(HandymanContext.class))).thenAnswer(new Answer<HandymanContext>() {
            @Override
            public HandymanContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (HandymanContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccessAndTheLineContainsTwoElements() {
        // Given
        HandymanValidator validator = givenValidator(true);
        HandymanDetailsController controller = givenController();
        DetailsHandyman command = new DetailsHandyman(validator, controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailedAndTheLineContainsTwoElements() {
        // Given
        HandymanValidator validator = givenValidator(false);
        HandymanDetailsController controller = givenController();
        DetailsHandyman command = new DetailsHandyman(validator, controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
    
     @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccessAndTheLineContainsThreeElements() {
        // Given
        HandymanValidator validator = givenValidator(true);
        HandymanDetailsController controller = givenController();
        DetailsHandyman command = new DetailsHandyman(validator, controller);
        String[] cmd = new String[3];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailedAndTheLineContainsThreeElements() {
        // Given
        HandymanValidator validator = givenValidator(false);
        HandymanDetailsController controller = givenController();
        DetailsHandyman command = new DetailsHandyman(validator, controller);
        String[] cmd = new String[3];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
}
