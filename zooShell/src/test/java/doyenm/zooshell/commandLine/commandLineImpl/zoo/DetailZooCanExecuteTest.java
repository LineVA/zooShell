package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DetailZooCanExecuteTest {

    private final String ZOO = "zoo";

    @Test
    public void shouldReturnTrueWhenTheCommandIsZoo() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {this.ZOO};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheFrstElementIsNotZoo() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {TestUtils.generateString()};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElement() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {this.ZOO, TestUtils.generateString()};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
