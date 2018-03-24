package doyenm.zooshell.commandline.commandLineImpl.handyman;

import doyenm.zooshell.commandline.commandImpl.animal.LsAnimal;
import doyenm.zooshell.commandline.commandImpl.handyman.LsHandyman;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsHandymanCanExecuteTest {

    private final String HANDYMEN = "handymen";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsHandyman cmdImpl = new LsHandyman();
        String[] cmd = {this.HANDYMEN};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsHandyman cmdImpl = new LsHandyman();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsHandyman cmdImpl = new LsHandyman();
        String[] cmd = {this.HANDYMEN, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotHandymen() {
        // Given
        LsHandyman cmdImpl = new LsHandyman();
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
