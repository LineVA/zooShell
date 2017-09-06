package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.animal.DetailAnimal;
import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
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
public class DetailZooExecuteTest {

    private ZooDetailsController givenController() {
        ZooDetailsController controller = Mockito.mock(ZooDetailsController.class);
        Mockito.when(controller.apply(any(ZooContext.class))).thenAnswer(new Answer<ZooContext>() {
            @Override
            public ZooContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (ZooContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccessAndTheLineContainsOneElement() {
        // Given
        ZooDetailsController controller = givenController();
        DetailZoo command = new DetailZoo(controller);
        String[] cmd = new String[1];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
    }

     @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccessAndTheLineContainsTwoElements() {
        // Given
        ZooDetailsController controller = givenController();
        DetailZoo command = new DetailZoo(controller);
        String[] cmd = new String[2];
        // When
        ReturnExec result = command.execute(cmd, Mockito.mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
    }

}
