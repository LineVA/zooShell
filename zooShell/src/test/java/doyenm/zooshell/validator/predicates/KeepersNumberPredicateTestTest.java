package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class KeepersNumberPredicateTestTest {

    private Map<String, Paddock> givenPaddocksMap(int number) {
        Map<String, Paddock> map = new HashMap<>();
        for (int i = 0; i < number; i++) {
            map.put(RandomStringUtils.random(20), Mockito.mock(Paddock.class));
        }
        return map;
    }

    private Map<String, AnimalKeeper> givenKeepersMap(int number) {
        Map<String, AnimalKeeper> map = new HashMap<>();
        for (int i = 0; i < number; i++) {
            map.put(RandomStringUtils.random(20), Mockito.mock(AnimalKeeper.class));
        }
        return map;
    }

    private KeeperCreationContext givenContext(Map<String, Paddock> paddocks,
            Map<String, AnimalKeeper> keepers) {
        KeeperCreationContext context = Mockito.mock(KeeperCreationContext.class);
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        Mockito.when(context.getPaddocks()).thenReturn(paddocks);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenThereIsExactlyOneKeeperFreePlaceAndAnEvenNumberOfPaddocks() {
        // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(4);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(1);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsExactlyOneKeeperFreePlaceAndAnOddNumberOfPaddocks() {
        // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(3);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(1);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsMoreThanOneKeeperFreePlaceAndAnEvenNumberOfPaddocks() {
         // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(4);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(0);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenThereIsMoreThanOneKeeperFreePlaceAndAnOddNumberOfPaddocks() {
         // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(3);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(0);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoKeeperFreePlaceAndAnEvenNumberOfPaddocks() {
       // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(0);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(0);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoKeeperFreePlaceAndAnOddNumberOfPaddocks() {
       // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(3);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(2);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoPaddock() {
        // Given
        Map<String, Paddock> paddocks = givenPaddocksMap(4);
        Map<String, AnimalKeeper> keepers = givenKeepersMap(2);
        KeeperCreationContext context = givenContext(paddocks, keepers);
        KeepersNumberPredicate predicate = new KeepersNumberPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
