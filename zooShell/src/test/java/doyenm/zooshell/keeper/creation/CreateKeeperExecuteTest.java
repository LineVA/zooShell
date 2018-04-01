package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateKeeperExecuteTest {

    private CreateKeeper subject;
    private KeeperCreationValidator validator;
    private KeeperCreationController controller;

    @Before
    public void setUp() {
        validator = mock(KeeperCreationValidator.class);
        controller = mock(KeeperCreationController.class);
        KeeperCreationContext context = mock(KeeperCreationContext.class);
        when(controller.apply(any())).thenReturn(context);
        subject = new CreateKeeper(validator, controller);
    }


    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        when(validator.test(any())).thenReturn(true);
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
        Assertions.assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCCreationValidatorReturnsFalse() {
        // Given
        when(validator.test(any())).thenReturn(false);
        String[] cmd = new String[7];
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        Assertions.assertThat(result.getMessage()).isNotEmpty();
    }
}
