package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.zoo.rename.RenameZoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class RenameZooCanExecuteTest {

    private final static String ZOO = "zoo";
    private final static String RENAME = "rename";

    private RenameZoo subject;

    @Before
    public void setUp(){
        subject = new RenameZoo(null, null);
    }

    @Test
    public void shouldReturnTrueWhenTheElementsAreCorrect() {
        // Given
        String[] cmd = {ZOO, RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessthanThreeElements() {
        // Given
        String[] cmd = {ZOO, RENAME};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanThreeElements() {
        // Given
        String[] cmd = {ZOO, RENAME,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsIncorrect() {
        // Given
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RENAME, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementIsIncorrect() {
        // Given
        String[] cmd = {ZOO, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = subject.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
