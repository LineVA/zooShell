package doyenm.zooshell.actionpoint;

import doyenm.zooshell.actionpoint.GetActionPoints;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class GetActionPointsCanExecuteTest {
    private final String ACTION_POINTS = "action-points";

    private final GetActionPoints subject = new GetActionPoints();


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        String[] cmd = {this.ACTION_POINTS};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotActionPoints() {
        // Given
        String falseActionPoints = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {falseActionPoints};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        assertThat(falseActionPoints).isNotEqualToIgnoringCase(ACTION_POINTS);
        assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanOneElement() {
        // Given
        String[] cmd = {};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElements() {
        // Given
        String[] cmd = {this.ACTION_POINTS,
                RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
