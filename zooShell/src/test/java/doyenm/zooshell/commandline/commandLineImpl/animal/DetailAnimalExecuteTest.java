package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.commandline.commandImpl.animal.DetailAnimal;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.AnimalValidator;
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
public class DetailAnimalExecuteTest {

    private AnimalValidator givenValidator(boolean value) {
        AnimalValidator validator = Mockito.mock(AnimalValidator.class);
        Mockito.doReturn(value).when(validator).test(any(AnimalContext.class));
        return validator;
    }

    private AnimalDetailsController givenController() {
        AnimalDetailsController controller = Mockito.mock(AnimalDetailsController.class);
        Mockito.when(controller.apply(any(AnimalContext.class))).thenAnswer(new Answer<AnimalContext>() {
            @Override
            public AnimalContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (AnimalContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccessAndTheLineContainsTwoElements() {
        // Given
        AnimalValidator validator = givenValidator(true);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
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
        AnimalValidator validator = givenValidator(false);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
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
        AnimalValidator validator = givenValidator(true);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
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
        AnimalValidator validator = givenValidator(false);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
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
