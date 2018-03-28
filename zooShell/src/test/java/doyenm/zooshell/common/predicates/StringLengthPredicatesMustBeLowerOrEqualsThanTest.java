package doyenm.zooshell.common.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class StringLengthPredicatesMustBeLowerOrEqualsThanTest {
   @Test
    public void shouldReturnFalseWhenTheValueIsGreaterThanTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "123456";
        int min = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "12345";
        int min = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheValueIsLowerThanTheReference() {
        // Given
        StringLengthPredicates predicate = new StringLengthPredicates();
        String testing = "1234";
        int min = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
