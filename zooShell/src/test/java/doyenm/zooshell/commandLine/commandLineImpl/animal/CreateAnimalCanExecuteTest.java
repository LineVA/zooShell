package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.CreateAnimal;
import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class CreateAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String CREATE = "create";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        Play play = givenPlay();
        CreateAnimal createAnimal = new CreateAnimal(play);
        String[] cmd = {this.ANIMAL, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotAnimal() {
        // Given
        Play play = givenPlay();
        CreateAnimal createAnimal = new CreateAnimal(play);
        String falseAnimal = TestUtils.generateString();
        String[] cmd = {falseAnimal, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(ANIMAL);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        Play play = givenPlay();
        CreateAnimal createAnimal = new CreateAnimal(play);
        String falseCreate = TestUtils.generateString();
        String[] cmd = {this.ANIMAL, falseCreate, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanSixElements() {
        // Given
        Play play = givenPlay();
        CreateAnimal createAnimal = new CreateAnimal(play);
        String[] cmd = {this.ANIMAL, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSixElements() {
        // Given
        Play play = givenPlay();
        CreateAnimal createAnimal = new CreateAnimal(play);
        String[] cmd = {this.ANIMAL, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
