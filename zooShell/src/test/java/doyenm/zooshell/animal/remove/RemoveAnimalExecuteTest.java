package doyenm.zooshell.animal.remove;

import doyenm.zooshell.animal.AnimalContext;
import doyenm.zooshell.animal.AnimalValidator;
import doyenm.zooshell.animal.rename.AnimalRenameContext;
import doyenm.zooshell.animal.rename.AnimalRenameController;
import doyenm.zooshell.animal.rename.AnimalRenameValidator;
import doyenm.zooshell.animal.rename.RenameAnimal;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RemoveAnimalExecuteTest {

    private RemoveAnimal subject;

    private String[] cmd = new String[3];

    private AnimalValidator validator;
    private AnimalRemoveController controller;

    @Before
    public void setUp() {
        validator = mock(AnimalValidator.class);
        controller = mock(AnimalRemoveController.class);
        AnimalContext context = mock(AnimalContext.class);
        when(controller.apply(any())).thenReturn(context);
        subject = new RemoveAnimal(validator, controller);
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
