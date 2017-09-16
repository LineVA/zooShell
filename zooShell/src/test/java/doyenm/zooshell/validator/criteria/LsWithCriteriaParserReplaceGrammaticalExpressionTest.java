package doyenm.zooshell.validator.criteria;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsWithCriteriaParserReplaceGrammaticalExpressionTest {

    @Test
    public void shouldReturnTheSameListWithTheAndOrNotReplaced() {
        // Given
        List<String> init = Arrays.asList("NOT", "not", "OR", "or", "AND", "And", ")");
        // When
        List<String> result = LsWithCriteriaParser.replaceGrammaticalExpression(init);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).containsExactly("!", "!", "||", "||", "&&", "&&", ")");
    }
}
