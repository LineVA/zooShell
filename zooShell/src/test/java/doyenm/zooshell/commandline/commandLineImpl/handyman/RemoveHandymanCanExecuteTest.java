package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.commandline.commandImpl.handyman.RemoveHandyman;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RemoveHandymanCanExecuteTest {

    private final String HANDYMAN = "handyman";
    private final String REMOVE = "remove"; 
    private final String HD = "hd";


    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHandyman() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHd() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {this.HD, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.REMOVE,
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsIncorrect() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        RemoveHandyman commandImpl = new RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
