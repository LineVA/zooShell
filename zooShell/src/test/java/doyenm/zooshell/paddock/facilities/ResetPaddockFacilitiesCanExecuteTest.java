package doyenm.zooshell.paddock.facilities;

import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * @author doyenm
 */
public class ResetPaddockFacilitiesCanExecuteTest {

    private final String PADDOCK = "paddock-facility";
    private final String PAD = "pad-facility";
    private final String RESET = "reset";

    private final ResetPaddockFacilities subject = new ResetPaddockFacilities(null, null);


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddock() {
        // Given
        String[] cmd = {this.PADDOCK, this.RESET,
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPad() {
        // Given
        String[] cmd = {this.PAD, this.RESET,
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
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.RESET,
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(falsePad).isNotEqualToIgnoringCase(PADDOCK);
        Assertions.assertThat(falsePad).isNotEqualToIgnoringCase(PAD);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotRESET() {
        // Given
        String falseReset = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.PADDOCK, falseReset,
                RandomStringUtils.randomAlphabetic(10), };
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(falseReset).isNotEqualToIgnoringCase(RESET);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Given
        String[] cmd = {this.PADDOCK, this.RESET};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        String[] cmd = {this.PADDOCK, this.RESET,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
