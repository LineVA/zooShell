package doyenm.zooshell.validator.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class StringLengthPredicatesMustBeGreaterOrEqualsThanTest {

    @Test
    public void shouldReturnTrueWhenTheValueIsGreaterThanTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "123456";
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "12345";
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheValueIsLowerThanTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "1234";
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
