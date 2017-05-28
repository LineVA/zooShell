package doyenm.zooshell.validator.function;

import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class ReplaceSpacesWithUnderscoresReplaceTest {

    @Test
    public void shouldDoNothing() {
        // Given
        String input = TestUtils.generateString();
        ReplaceSpacesWithUnderscores operator = new ReplaceSpacesWithUnderscores();
        // When
        String output = operator.replace(input);
        // Then
        Assertions.assertThat(output).isEqualTo(input);
    }

    @Test
    public void shouldReplaceOneSpaceByOneUnderscore() {
        // Given
        String input_1 = TestUtils.generateString();
        String input_2 = TestUtils.generateString();
        String input = input_1 + " " + input_2;
        ReplaceSpacesWithUnderscores operator = new ReplaceSpacesWithUnderscores();
        // When
        String output = operator.replace(input);
        // Then
        Assertions.assertThat(output).isEqualTo(input_1 + "_" + input_2);
    }

    @Test
    public void shouldReplaceTwoSpacesByTwoUnderscores() {
        // Given
        String input_1 = TestUtils.generateString();
        String input_2 = TestUtils.generateString();
        String input_3 = TestUtils.generateString();
        String input = input_1 + " " + input_2 + " " + input_3;
        ReplaceSpacesWithUnderscores operator = new ReplaceSpacesWithUnderscores();
        // When
        String output = operator.replace(input);
        // Then
        Assertions.assertThat(output).isEqualTo(input_1 + "_" + input_2 + "_" + input_3);
    }
}