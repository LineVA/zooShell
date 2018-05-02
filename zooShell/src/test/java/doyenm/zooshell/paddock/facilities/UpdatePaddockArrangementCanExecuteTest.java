package doyenm.zooshell.paddock.facilities;

import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author doyenm
 */
public class UpdatePaddockArrangementCanExecuteTest {

    private final String PADDOCK = "paddock-arrangement";
    private final String PAD = "pad-arrangement";
    private final String UPDATE = "update";

    private final UpdatePaddockArrangements subject = new UpdatePaddockArrangements(null, null);


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddock() {
        // Given
        String[] cmd = {this.PADDOCK, this.UPDATE,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPad() {
        // Given
        String[] cmd = {this.PAD, this.UPDATE, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddockArrangement() {
        // Given
        String falsePad = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.UPDATE,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(falsePad).isNotEqualToIgnoringCase(PADDOCK);
        Assertions.assertThat(falsePad).isNotEqualToIgnoringCase(PAD);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        String falseUpdate = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.PADDOCK, falseUpdate,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(falseUpdate).isNotEqualToIgnoringCase(UPDATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        String[] cmd = {this.PADDOCK, this.UPDATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenThereIsMoreThanFourElementsAndTheyBeginsWithTheExpectedValues() {
        // Given
        String[] cmd = {this.PADDOCK, this.UPDATE,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

}
