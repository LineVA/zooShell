package doyenm.zooshell.paddock.facilities;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author doyenm
 */
public class UpdatePaddockFacilityCanExecuteTest {

    private final String PADDOCK = "paddock-facility";
    private final String PAD = "pad-facility";
    private final String ADD = "add";
    private final String REMOVE = "remove";

    private final UpdatePaddockFacilities subject = new UpdatePaddockFacilities(null, null, null);


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddockAndAdd() {
        // Given
        String[] cmd = {this.PADDOCK, this.ADD,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPadAndRemove() {
        // Given
        String[] cmd = {this.PAD, this.REMOVE, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddockAndRemove() {
        // Given
        String[] cmd = {this.PADDOCK, this.REMOVE,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPadAndAdd() {
        // Given
        String[] cmd = {this.PAD, this.ADD, RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadNorPaddockFacility() {
        // Given
        String falsePad = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.ADD,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(falsePad).isNotEqualToIgnoringCase(PADDOCK);
        assertThat(falsePad).isNotEqualToIgnoringCase(PAD);
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotAddNorRemove() {
        // Given
        String falseAction = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.PADDOCK, falseAction,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(falseAction).isNotEqualToIgnoringCase(ADD);
        assertThat(falseAction).isNotEqualToIgnoringCase(REMOVE);
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        String[] cmd = {this.PADDOCK, this.ADD, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenThereIsMoreThanFourElementsAndTheyBeginsWithTheExpectedValues() {
        // Given
        String[] cmd = {this.PADDOCK, this.ADD,
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

}
