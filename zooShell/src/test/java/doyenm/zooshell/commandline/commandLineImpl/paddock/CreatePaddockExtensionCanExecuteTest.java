package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandImpl.paddock.CreatePaddockExtension;
import org.apache.commons.lang.RandomStringUtils;
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PAD, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddockExtension() {
        // Given
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
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
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        CreatePaddockExtension createPaddock = new CreatePaddockExtension(null, null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
