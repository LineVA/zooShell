package doyenm.zooshell.animal.move;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoveAnimalExecuteTest {

    private ChangePaddock subject;

    private String[] cmd = new String[3];

    private AnimalChangePaddockValidator validator;
    private AnimalChangePaddockController controller;

    @Before
    public void setUp() {
        validator = mock(AnimalChangePaddockValidator.class);
        controller = mock(AnimalChangePaddockController.class);
        AnimalChangePaddockContext context = mock(AnimalChangePaddockContext.class);
        when(controller.apply(any())).thenReturn(context);
        subject = new ChangePaddock(validator, controller);
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
