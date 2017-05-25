package doyenm.zooshell.commandLine.commandLineImpl;

import doyenm.zooshell.commandLine.commandImpl.CreateZoo;
import doyenm.zooshell.launch.play.Play;
import java.util.Random;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class CreateZooCanExecuteTest {

    private final String ZOO = "zoo";
    private final String CREATE = "create";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    private String generateString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890&é(-è__çà".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private String[] givenCorrectCmdWithXElements(int x) {
        String[] cmd = new String[x + 2];
        cmd[0] = this.ZOO;
        cmd[1] = this.CREATE;
        for (int i = 0; i < x; i++) {
            cmd[i+2] = this.generateString();
        }
        return cmd;
    }
    
    private String[] givenIncorrectCmdWithXElements(String first, String second, int x) {
        String[] cmd = new String[x + 2];
        cmd[0] = first;
        cmd[1] = second;
        for (int i = 0; i < x; i++) {
            cmd[i] = this.generateString();
        }
        return cmd;
    }

    @Test
    public void shouldReturnTrueWhenThereIsThreeElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(1);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsFiveElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(3);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsSevenElementsInTheCommandAndWhenItBeginsCorrectly() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(5);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsSixElements() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(4);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsFourElements() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenCorrectCmdWithXElements(2);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotZoo() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenIncorrectCmdWithXElements(this.generateString(), this.CREATE, 6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
        @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotCreate() {
        // Given
        Play play = givenPlay();
        CreateZoo createZoo = new CreateZoo(play);
        String[] cmd = this.givenIncorrectCmdWithXElements(this.ZOO, this.generateString(), 6);
        // When
        boolean actualResult = createZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
