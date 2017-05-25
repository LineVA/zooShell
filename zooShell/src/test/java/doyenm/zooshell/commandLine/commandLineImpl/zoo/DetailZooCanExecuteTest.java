package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class DetailZooCanExecuteTest {

    private final String ZOO = "zoo";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsZoo() {
        // Given
        Play play = givenPlay();
        DetailZoo detailZoo = new DetailZoo(play);
        String[] cmd = {this.ZOO};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFrstElementIsNotZoo() {
        // Given
        Play play = givenPlay();
        DetailZoo detailZoo = new DetailZoo(play);
        String[] cmd = {TestUtils.generateString()};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElement() {
        // Given
        Play play = givenPlay();
        DetailZoo detailZoo = new DetailZoo(play);
        String[] cmd = {this.ZOO, TestUtils.generateString()};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
