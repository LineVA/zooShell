package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.DetailAnimal;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.AnimalDetailsValidator;
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

    private AnimalDetailsValidator givenValidator(boolean value) {
        AnimalDetailsValidator validator = Mockito.mock(AnimalDetailsValidator.class);
        Mockito.doReturn(value).when(validator).test(any(AnimalDetailsContext.class));
        return validator;
    }

    private AnimalDetailsController givenController() {
        AnimalDetailsController controller = Mockito.mock(AnimalDetailsController.class);
        Mockito.when(controller.apply(any(AnimalDetailsContext.class))).thenAnswer(new Answer<AnimalDetailsContext>() {
            @Override
            public AnimalDetailsContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (AnimalDetailsContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        AnimalDetailsValidator validator = givenValidator(true);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToFailedWhenTheCommandIsFailed() {
        // Given
        AnimalDetailsValidator validator = givenValidator(false);
        AnimalDetailsController controller = givenController();
        DetailAnimal command = new DetailAnimal(validator, controller);
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }
}
