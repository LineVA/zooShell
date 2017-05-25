package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.validator.context.OverlapContext;
import java.util.function.Function;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class CanOverlapPredicateTestTest {

    private OverlapContext givenOverlapContext(int value, int size) {
        OverlapContext context = Mockito.mock(OverlapContext.class);
        Mockito.when(context.getFirstValue()).thenReturn(10);
        Mockito.when(context.getFirstSize()).thenReturn(10);
        Mockito.when(context.getSecondValue()).thenReturn(value);
        Mockito.when(context.getSecondSize()).thenReturn(size);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenBothComparisionsAreTrue() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(20, 10);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenBothsComparisionsAreTrueTheFirstWithEquality() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(5, 5);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenBothsComparisionsAreTrueTheSecondWithEquality() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(20, 1);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenBothsComparisionsAreTrueWithEquality() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(20, -10);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenFirstComparisionIsTrueButTheSecondIsFalse() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(25, -5);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenFirstComparisionIsFalseButTheSecondIsTrue() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(5, 0);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenBothComparisionAreFalse() {
        // Given
        CanOverlapPredicate predicate = new CanOverlapPredicate();
        OverlapContext context = givenOverlapContext(25, -20);
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

//    @Test
//    public void toto() {
//        Stream.of(new CanOverlapPredicate())
//                .map(new Function<CanOverlapPredicate, CanOverlapPredicate>() {
//
//                    @Override
//                    public CanOverlapPredicate apply(CanOverlapPredicate t) {
//                        return null;
//                    }
//                })
//                .findFirst()
//                .get();
//    }
}
