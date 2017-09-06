package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import org.apache.commons.lang.RandomStringUtils;
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
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPad() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PAD, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddock() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String falseAnimal = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
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
        String falseCreate = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.PADDOCK, falseCreate, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
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
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = CreatePaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        CreatePaddock createPaddock = new CreatePaddock(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
