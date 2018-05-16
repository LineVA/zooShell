package doyenm.zooshell.paddock.facilities;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author doyenm
 */
public class LsPaddockFacilitiesCanExecuteTest {

    private final String FACILITIES = "facilities";

    private final LsPaddockFacilities subject = new LsPaddockFacilities();


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        String[] cmd = {this.FACILITIES};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotFacilities() {
        // Given
        String falsePad = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(falsePad).isNotEqualToIgnoringCase(FACILITIES);
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
        String[] cmd = {this.FACILITIES,
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isFalse();
    }

}
