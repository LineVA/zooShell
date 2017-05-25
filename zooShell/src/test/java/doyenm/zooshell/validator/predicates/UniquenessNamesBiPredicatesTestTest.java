package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.testUtils.TestUtils;
import java.util.HashSet;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class UniquenessNamesBiPredicatesTestTest {

    @Test
    public void shouldReturnTrueWhenTheStringDoesNotExistInTheSet() {
        // Given
        String str_1 = TestUtils.generateString();
        String str_2 = TestUtils.generateString();
        String str_3 = TestUtils.generateString();
        Assertions.assertThat(str_1).isNotEqualTo(str_2);
        Assertions.assertThat(str_1).isNotEqualTo(str_3);
        Set<String> set = new HashSet<>();
        set.add(str_2);
        set.add(str_3);
        UniquenessNamesBiPredicates predicate = new UniquenessNamesBiPredicates();
        // When
        boolean result = predicate.test(str_1, set);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheStringAlreadyExistsInTheSet() {
        // Given
        String str_1 = TestUtils.generateString();
        String str_2 = TestUtils.generateString();
        Assertions.assertThat(str_1).isNotEqualTo(str_2);
        Set<String> set = new HashSet<>();
        set.add(str_2);
        set.add(str_1);
        UniquenessNamesBiPredicates predicate = new UniquenessNamesBiPredicates();
        // When
        boolean result = predicate.test(str_1, set);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
