package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.handyman.rename.RenameHandyman;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RenameHandymanCanExecuteTest {

    private final String HD = "hd";
    private final String HANDYMAN = "handyman";
    private final String RENAME = "rename";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByHandyman() {
        // Given
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {this.HANDYMAN, this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByHd() {
        // Given
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {this.HD, this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {this.HD, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Give
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.RENAME, RandomStringUtils.randomAlphabetic(10),};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        RenameHandyman cmdLine = new RenameHandyman(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.RENAME, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdLine.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
