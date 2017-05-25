package doyenm.zooshell.validator.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class IntegerValuePredicatesMustBeLowerOrEqualsThanTest {
@Test
    public void shouldReturnFalseWhenTheValueIsGreaterThanTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 10;
        int max = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 5;
        int max = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnTrueWhenTheValueIsLowerThanTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 0;
        int max = 5;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
