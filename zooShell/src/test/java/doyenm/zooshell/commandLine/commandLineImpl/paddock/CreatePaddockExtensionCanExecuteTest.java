package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddockExtension;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreatePaddockExtensionCanExecuteTest {

    private final String PADDOCK = "paddock-extension";
    private final String PAD = "pad-extension";
    private final String CREATE = "create";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPaddock() {
        // Given
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PAD, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddockExtension() {
        // Given
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
