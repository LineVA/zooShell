package doyenm.zooshell.commandline.commandLineImpl.keeper;

import doyenm.zooshell.commandline.commandimpl.keeper.AddTraining;
import doyenm.zooshell.commandline.commandimpl.paddock.CreatePaddock;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.KeeperAddTrainingContext;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.keepercontroller.KeeperAddTrainingController;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.KeeperAddTrainingValidator;
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
public class AddTrainingExecuteTest {

    private KeeperAddTrainingValidator givenValidator(boolean value) {
        KeeperAddTrainingValidator validator = Mockito.mock(KeeperAddTrainingValidator.class);
        Mockito.doReturn(value).when(validator).test(any(KeeperAddTrainingContext.class));
        return validator;
    }

    private KeeperAddTrainingController givenController() {
        KeeperAddTrainingController controller = Mockito.mock(KeeperAddTrainingController.class);
        Mockito.when(controller.apply(any(KeeperAddTrainingContext.class))).thenAnswer(new Answer<KeeperAddTrainingContext>() {
            @Override
            public KeeperAddTrainingContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (KeeperAddTrainingContext) args[0];
            }
        });
        return controller;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        KeeperAddTrainingValidator validator = givenValidator(true);
        KeeperAddTrainingController controller = givenController();
        AddTraining command = new AddTraining(validator, controller);
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
        KeeperAddTrainingValidator validator = givenValidator(false);
        KeeperAddTrainingController controller = givenController();
        AddTraining command = new AddTraining(validator, controller);
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