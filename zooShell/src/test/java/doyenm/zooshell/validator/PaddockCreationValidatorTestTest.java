package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.HashSet;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class PaddockCreationValidatorTestTest {

    private StringLengthPredicates givenStringPredicates(boolean value) {
        StringLengthPredicates mock = Mockito.mock(StringLengthPredicates.class);
        Mockito.when(mock.mustBeLowerOrEqualsThan(anyString(), anyInt())).thenReturn(value);
        return mock;
    }

    private UniquenessNamesBiPredicates givenUniquenessNames(boolean value) {
        UniquenessNamesBiPredicates mock = Mockito.mock(UniquenessNamesBiPredicates.class);
        Mockito.when(mock.test(anyString(), anySet())).thenReturn(value);
        return mock;
    }

    private IntegerValuePredicates givenIntPredicates(boolean value, boolean value2) {
        IntegerValuePredicates mock = Mockito.mock(IntegerValuePredicates.class);
        Mockito.when(mock.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(value).thenReturn(value2);
        return mock;
    }

    private PaddockCreationContext givenContext() {
        PaddockCreationContext mock = Mockito.mock(PaddockCreationContext.class);
        Mockito.when(mock.getConvertedHeight()).thenReturn(RandomUtils.nextInt());
        Mockito.when(mock.getConvertedWidth()).thenReturn(RandomUtils.nextInt());
        Mockito.doNothing().when(mock).convert();
        Mockito.when(mock.getName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(mock.getPaddocksNameSet()).thenReturn(new HashSet<>());
        return mock;
    }

    /**
     * Conditions : - the conversion is faisable - the name is shorter than 50
     * characters - the width is greater or equals than 1 - the height is
     * greater or equals than 1 - the name is unique
     */
    @Test
    public void shouldReturnTrueIfAllConditionsAreTrue() {
        // GIven
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessNames(true);
        IntegerValuePredicates integerValuePredicates = givenIntPredicates(true, true);
        PaddockCreationContext context = givenContext();
        PaddockCreationValidator validator = new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                RandomUtils.nextInt(), 
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnFalseWhenTheNameLengthDoesNotRespectTheExpectations() {
        // GIven
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(false);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessNames(true);
        IntegerValuePredicates integerValuePredicates = givenIntPredicates(true, true);
        PaddockCreationContext context = givenContext();
        PaddockCreationValidator validator = new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                RandomUtils.nextInt(), 
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
         @Test
    public void shouldReturnFalseWhenTheNameIsNotUnique() {
        // GIven
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessNames(false);
        IntegerValuePredicates integerValuePredicates = givenIntPredicates(true, true);
        PaddockCreationContext context = givenContext();
        PaddockCreationValidator validator = new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                RandomUtils.nextInt(), 
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
       @Test
    public void shouldReturnFalseWhenTheWidthDoesNotRespectTheExpectations() {
        // GIven
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessNames(true);
        IntegerValuePredicates integerValuePredicates = givenIntPredicates(true, false);
        PaddockCreationContext context = givenContext();
        PaddockCreationValidator validator = new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                RandomUtils.nextInt(), 
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheHeightDoesNotRespectTheExpectations() {
        // GIven
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessNames(true);
        IntegerValuePredicates integerValuePredicates = givenIntPredicates(false, true);
        PaddockCreationContext context = givenContext();
        PaddockCreationValidator validator = new PaddockCreationValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                integerValuePredicates,
                RandomUtils.nextInt(), 
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
