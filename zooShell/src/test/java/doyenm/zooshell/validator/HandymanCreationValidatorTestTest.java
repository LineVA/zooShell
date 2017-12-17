package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import java.util.HashSet;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mockito;
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

    /**
     * Conditions :  the name is OK
     */
    @Test
    public void shouldReturnTrueIfAllConditionsAreTrue() {
        // GIven
        NameValidator nameValidator = givenNameTest(true);
        HandymanCreationContext context = givenContext();
        HandymanCreationValidator validator = new HandymanCreationValidator(
                nameValidator);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNameDoesNotRespectTheExpectations() {
        // GIven
        NameValidator nameValidator = givenNameTest(false);
        HandymanCreationContext context = givenContext();
        HandymanCreationValidator validator = new HandymanCreationValidator(
                nameValidator);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
