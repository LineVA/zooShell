package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.handyman.occupations.UpdateOccupations;
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
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHd() {
        // Given
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
        String[] cmd = {this.HD, this.REMOVE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
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
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
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
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        UpdateOccupations.RemoveHandyman commandImpl = new UpdateOccupations.RemoveHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.REMOVE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
