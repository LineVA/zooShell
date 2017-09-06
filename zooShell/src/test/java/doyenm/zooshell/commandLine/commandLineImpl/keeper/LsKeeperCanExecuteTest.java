package doyenm.zooshell.commandLine.commandLineImpl.keeper;

import doyenm.zooshell.commandLine.commandImpl.keeper.LsKeeper;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsKeeperCanExecuteTest {

    private final String KEEPERS = "keepers";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        LsKeeper cmdImpl = new LsKeeper();
        String[] cmd = {this.KEEPERS};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooShort() {
        // Given
        LsKeeper cmdImpl = new LsKeeper();
        String[] cmd = {};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        LsKeeper cmdImpl = new LsKeeper();
        String[] cmd = {this.KEEPERS, TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementOfTheCommandIsNotKeepers() {
        // Given
        LsKeeper cmdImpl = new LsKeeper();
        String[] cmd = {TestUtils.generateString()};
        // When
        boolean actualResult = cmdImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
