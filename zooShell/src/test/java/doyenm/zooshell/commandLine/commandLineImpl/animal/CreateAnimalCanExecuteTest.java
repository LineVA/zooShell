package doyenm.zooshell.commandLine.commandLineImpl.animal;

import doyenm.zooshell.commandLine.commandImpl.animal.CreateAnimal;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreateAnimalCanExecuteTest {

    private final String ANIMAL = "animal";
    private final String CREATE = "create";
    TestUtils testUtils = new TestUtils();

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        CreateAnimal createAnimal = new CreateAnimal(null, null);
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
        CreateAnimal createAnimal = new CreateAnimal(null, null);
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
        CreateAnimal createAnimal = new CreateAnimal(null, null);
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
        CreateAnimal createAnimal = new CreateAnimal(null, null);
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
        CreateAnimal createAnimal = new CreateAnimal(null, null);
        String[] cmd = {this.ANIMAL, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createAnimal.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
