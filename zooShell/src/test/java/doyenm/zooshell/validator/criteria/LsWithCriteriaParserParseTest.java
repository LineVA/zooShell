package doyenm.zooshell.validator.criteria;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class LsWithCriteriaParserParseTest {

    
    @Test
    public void shouldReturnTheSameListWithTheGivenElementsExcluded(){
        // Given
        List<String> excluded = Arrays.asList("A", "B");
        List<String> init = Arrays.asList("A", "a", "B", "c", "Ad");
        // When
        List<String> result = LsWithCriteriaParser.parse(init, excluded);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).containsExactlyInAnyOrder("c", "Ad");
    }
}
