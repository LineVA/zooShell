package doyenm.zooshell.commandLine.commandLineImpl.handyman;

import doyenm.zooshell.commandLine.commandImpl.handyman.CreateHandyman;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreateHandymanCanExecuteTest {

    private final String HANDYMAN = "handyman";
    private final String HD = "hd";
    private final String CREATE = "create";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHandyman() {
        // Given
        CreateHandyman createHandyman = new CreateHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.CREATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithHD() {
        // Given
        CreateHandyman createHandyman = new CreateHandyman(null, null);
        String[] cmd = {this.HD, this.CREATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotHandymanOrHD() {
        // Given
        CreateHandyman createHandyman = new CreateHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.CREATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        CreateHandyman createHandyman = new CreateHandyman(null, null);
        String falseCreate = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.HANDYMAN, falseCreate, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanThreeElements() {
        // Given
        CreateHandyman CreateHandyman = new CreateHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.CREATE};
        // When
        boolean actualResult = CreateHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        CreateHandyman createHandyman = new CreateHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.CREATE, 
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createHandyman.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
