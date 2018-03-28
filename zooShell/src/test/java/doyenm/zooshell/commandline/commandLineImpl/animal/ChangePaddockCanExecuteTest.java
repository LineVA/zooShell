package doyenm.zooshell.commandline.commandLineImpl.animal;

import doyenm.zooshell.animal.move.ChangePaddock;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class ChangePaddockCanExecuteTest {

    private final String ANIMAL_PADDOCK = "animal-paddock";
    private final String ANIMAL_PAD = "animal-pad";
    private final String UPDATE = "update";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalPaddock() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {this.ANIMAL_PADDOCK, this.UPDATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalPad() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {this.ANIMAL_PAD, this.UPDATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.UPDATE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsIncorrect() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {this.ANIMAL_PAD, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {this.ANIMAL_PAD, this.UPDATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        ChangePaddock commandImpl = new ChangePaddock(null, null);
        String[] cmd = {this.ANIMAL_PAD, this.UPDATE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
