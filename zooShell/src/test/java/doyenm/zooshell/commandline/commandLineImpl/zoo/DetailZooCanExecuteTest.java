package doyenm.zooshell.commandline.commandLineImpl.zoo;

import doyenm.zooshell.commandline.commandimpl.zoo.DetailZoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DetailZooCanExecuteTest {

    private final String ZOO = "zoo";
    private final static String DETAILED = "detailed";

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
    public void shouldReturnTrueWhenTheCommandIsZooDetailed() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {this.ZOO, DETAILED};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheCommandContainsLessThanOneElement() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotZooAndTheCommandContainsOnlyOneElement() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotZooAndTheCommandContainsTwoElements() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), DETAILED};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotDetailed() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {this.ZOO, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanTwoElements() {
        // Given
        DetailZoo detailZoo = new DetailZoo(null);
        String[] cmd = {this.ZOO, DETAILED, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = detailZoo.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
