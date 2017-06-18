package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreatePaddockCanExecuteTest {

    private final String PADDOCK = "paddock";
    private final String PAD = "pad";
    private final String CREATE = "create";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPaddock() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPad() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PAD, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddock() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String falseAnimal = TestUtils.generateString();
        String[] cmd = {TestUtils.generateString(), this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(PADDOCK);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String falseCreate = TestUtils.generateString();
        String[] cmd = {this.PADDOCK, falseCreate, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanSevenElements() {
        // Given
        CreatePaddock CreatePaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = CreatePaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
