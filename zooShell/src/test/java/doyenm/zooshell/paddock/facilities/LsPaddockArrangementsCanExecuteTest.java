package doyenm.zooshell.paddock.facilities;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author doyenm
 */
public class LsPaddockArrangementsCanExecuteTest {

    private final String PADDOCK = "paddock-arrangement";
    private final String PAD = "pad-arrangement";

    private final LsPaddockArrangements subject = new LsPaddockArrangements();


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddock() {
        // Given
        String[] cmd = {this.PADDOCK};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPad() {
        // Given
        String[] cmd = {this.PAD};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddockArrangement() {
        // Given
        String falsePad = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(falsePad).isNotEqualToIgnoringCase(PADDOCK);
        assertThat(falsePad).isNotEqualToIgnoringCase(PAD);
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanOneElement() {
        // Given
        String[] cmd = {};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElement() {
        // Given
        String[] cmd = {this.PADDOCK,
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

}
