package doyenm.zooshell.validator;

import doyenm.zooshell.handyman.create.HandymanCreationContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.HandymenNumberPredicate;
import doyenm.zooshell.handyman.create.HandymanCreationValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class HandymanCreationValidatorTestTest {

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
        return mock;
    }

    private HandymanCreationContext givenContext() {
        HandymanCreationContext mock = mock(HandymanCreationContext.class);
        Mockito.when(mock.getName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(mock.getHandymenSet()).thenReturn(new HashSet<>());
        return mock;
    }

    private HandymenNumberPredicate givenPredicate(boolean result) {
        HandymenNumberPredicate mock = mock(HandymenNumberPredicate.class);
        Mockito.when(mock.test(any(HandymanCreationContext.class))).thenReturn(result);
        return mock;
    }

    /**
     * Conditions : the name is OK
     */
    @Test
    public void shouldReturnTrueIfAllConditionsAreTrue() {
        // GIven
        NameValidator nameValidator = givenNameTest(true);
        HandymenNumberPredicate predicate = givenPredicate(true);
        HandymanCreationContext context = givenContext();
        HandymanCreationValidator validator = new HandymanCreationValidator(
                nameValidator, predicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNameDoesNotRespectTheExpectations() {
        // GIven
        NameValidator nameValidator = givenNameTest(false);
        HandymenNumberPredicate predicate = givenPredicate(true);
        HandymanCreationContext context = givenContext();
        HandymanCreationValidator validator = new HandymanCreationValidator(
                nameValidator, predicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheNumberOfHandymenDoesNotRespectTheExpectations() {
        // GIven
        NameValidator nameValidator = givenNameTest(true);
        HandymenNumberPredicate predicate = givenPredicate(false);
        HandymanCreationContext context = givenContext();
        HandymanCreationValidator validator = new HandymanCreationValidator(
                nameValidator, predicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
