package doyenm.zooshell.common.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DoubleValuesPredicatesMustBeGreaterOrEqualsThanTest {

    @Test
    public void shouldReturnTrueWhenTheValueIsGreaterThanTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 5.1;
        double min = 5.0;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 5.0;
        double min = 5.0;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheValueIsLowerThanTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 4.9;
        double min = 5.0;
        // When
        boolean result = predicate.mustBeGreaterOrEqualsThan(testing, min);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
