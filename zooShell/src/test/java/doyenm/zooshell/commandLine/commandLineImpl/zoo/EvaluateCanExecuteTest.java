package doyenm.zooshell.commandLine.commandLineImpl.zoo;

import doyenm.zooshell.commandLine.commandImpl.zoo.Evaluate;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class EvaluateCanExecuteTest {

    private final static String EVALUATE = "evaluate";
    
    @Test
    public void shouldReturnTrueWhenTheOnlyElementIsCorrect(){
        // Given
        Evaluate evaluate = new Evaluate(null);
        String[] cmd = {EVALUATE};
        // When
        boolean actualResult = evaluate.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoElement() {
        // Given
        Evaluate evaluate = new Evaluate(null);
        String[] cmd = {};
        // When
        boolean actualResult = evaluate.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanOneElement() {
        // Given
        Evaluate evaluate = new Evaluate(null);
        String[] cmd = {EVALUATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = evaluate.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheOnlyElementIsIncorrect() {
        // Given
        Evaluate evaluate = new Evaluate(null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = evaluate.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
