package doyenm.zooshell.animal.rename;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.keeper.creation.CreateKeeper;
import doyenm.zooshell.keeper.creation.KeeperCreationContext;
import doyenm.zooshell.keeper.creation.KeeperCreationController;
import doyenm.zooshell.keeper.creation.KeeperCreationValidator;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RenameAnimalExecuteTest {

    private RenameAnimal subject;

    private String[] cmd = new String[7];

    private AnimalChangeNameValidator validator;
    private AnimalChangeNameController controller;

    @Before
    public void setUp() {
        validator = mock(AnimalChangeNameValidator.class);
        controller = mock(AnimalChangeNameController.class);
        AnimalChangeNameContext context = mock(AnimalChangeNameContext.class);
        when(controller.apply(any())).thenReturn(context);
        subject = new RenameAnimal(validator, controller);
    }


    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheCommandIsInSuccess() {
        // Given
        when(validator.test(any())).thenReturn(true);
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
        assertThat(result.getZoo()).isNotNull();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccessWhenTheValidatorReturnsFalse() {
        // Given
        when(validator.test(any())).thenReturn(false);
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.ERROR);
        assertThat(result.getMessage()).isNotEmpty();
    }
}
