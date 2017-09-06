package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.zoo.CreateZoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreateZooCanExecuteTest {

    private final String ZOO = "zoo";
    private final String CREATE = "create";

    private String[] givenCorrectCmdWithXElements(int x) {
        String[] cmd = new String[x + 2];
        cmd[0] = this.ZOO;
        cmd[1] = this.CREATE;
        for (int i = 0; i < x; i++) {
            cmd[i+2] = RandomStringUtils.randomAlphabetic(10);
        }
        return cmd;
    }
    
    private String[] givenIncorrectCmdWithXElements(String first, String second, int x) {
        String[] cmd = new String[x + 2];
        cmd[0] = first;
        cmd[1] = second;
        for (int i = 0; i < x; i++) {
            cmd[i] = RandomStringUtils.randomAlphabetic(10);
        }
        return cmd;
    }

    @Test
    public void shouldReturnTrueWhenThereIsThreeElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(1);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsFiveElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(3);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsSevenElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(5);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsSixElements() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(4);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsFourElements() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenCorrectCmdWithXElements(2);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotZoo() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenIncorrectCmdWithXElements(RandomStringUtils.randomAlphabetic(10), this.CREATE, 6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
        @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotCreate() {
        // Given
        CreateZoo createZoo = new CreateZoo(null, null);
        String[] cmd = this.givenIncorrectCmdWithXElements(this.ZOO, RandomStringUtils.randomAlphabetic(10), 6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
