package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.handyman.details.DetailsHandyman;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DetailsHandymanCanExecuteTest {

    private final String HANDYMAN = "handyman";
    private final String HD = "hd";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHandyman() {
        // Given
        DetailsHandyman cmdLine = new DetailsHandyman(null, null);
        String[] cmd = {this.HANDYMAN, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHd() {
        // Given
        DetailsHandyman cmdLine = new DetailsHandyman(null, null);
        String[] cmd = {this.HD, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        DetailsHandyman cmdLine = new DetailsHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanTwoElements() {
        // Give
        DetailsHandyman cmdLine = new DetailsHandyman(null, null);
        String[] cmd = {HANDYMAN};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

     @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        DetailsHandyman cmdLine = new DetailsHandyman(null, null);
        String[] cmd = {this.HANDYMAN, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
