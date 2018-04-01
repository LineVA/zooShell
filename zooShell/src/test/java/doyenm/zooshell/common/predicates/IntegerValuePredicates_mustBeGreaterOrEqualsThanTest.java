package doyenm.zooshell.common.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 *
 * @author doyenm
 */
public class IntegerValuePredicates_mustBeGreaterOrEqualsThanTest {

    @Test
    public void shouldReturnTrueWhenTheValueIsGreaterThanTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 10;
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 5;
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenTheValueIsLowerThanTheReference(){
        // Given
        IntegerValuePredicates predicate = new IntegerValuePredicates();
        int testing = 0;
        int min = 5;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
}
