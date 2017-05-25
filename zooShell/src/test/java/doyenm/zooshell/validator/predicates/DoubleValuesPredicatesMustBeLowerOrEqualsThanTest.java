package doyenm.zooshell.validator.predicates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class DoubleValuesPredicatesMustBeLowerOrEqualsThanTest {

    @Test
    public void shouldReturnFalseWhenTheValueIsGreaterThanTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 5.1;
        double max = 5.0;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheValueIsEqualToTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 5.0;
        double max = 5.0;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheValueIsLowerThanTheReference() {
        // Given
        DoubleValuesPredicates predicate = new DoubleValuesPredicates();
        double testing = 4.9;
        double max = 5.0;
        // When
        boolean result = predicate.mustBeLowerOrEqualsThan(testing, max);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
