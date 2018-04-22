package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class PaddockCreationValidatorTestTest {

    private PaddockCreationValidator subject;
    private NameValidator nameValidator;
    private IntegerValuePredicates integerPredicates;
    private int minHeight;
    private int minWidth;

    private PaddockCreationContext context;

    @Before
    public void setUp() {

        context = mock(PaddockCreationContext.class);
        when(context.getConvertedHeight()).thenReturn(RandomUtils.nextInt());
        when(context.getConvertedWidth()).thenReturn(RandomUtils.nextInt());
        doNothing().when(context).convert();
        when(context.getName()).thenReturn(RandomStringUtils.random(10));
        when(context.getPaddocksNameSet()).thenReturn(new HashSet<>());
        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        nameValidator = mock(NameValidator.class);
        integerPredicates = mock(IntegerValuePredicates.class);
        subject = new PaddockCreationValidator(nameValidator, integerPredicates, minHeight, minWidth);
    }

    /**
     * Conditions : - the conversion is faisable - the name is shorter than 50
     * characters - the width is greater or equals than 1 - the height is
     * greater or equals than 1 - the name is unique
     */
    @Test
    public void shouldReturnTrueIfAllConditionsAreTrue() {
        // Given
        when(integerPredicates.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(true).thenReturn(true);
        when(nameValidator.test(any(NameDto.class))).thenReturn(true);
        PaddockCreationValidator validator = new PaddockCreationValidator(
                nameValidator,
                integerPredicates,
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        PaddockCreationContext result = validator.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseWhenTheNameDoesNotRespectTheExpectations() {
        // Given
        when(integerPredicates.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(true).thenReturn(true);
        when(nameValidator.test(any(NameDto.class))).thenReturn(false);
        PaddockCreationValidator validator = new PaddockCreationValidator(
                nameValidator,
                integerPredicates,
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        PaddockCreationContext result = validator.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseWhenTheWidthDoesNotRespectTheExpectations() {
        // Given
        when(integerPredicates.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(true).thenReturn(false);
        when(nameValidator.test(any(NameDto.class))).thenReturn(true);
        PaddockCreationValidator validator = new PaddockCreationValidator(
                nameValidator,
                integerPredicates,
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        PaddockCreationContext result = validator.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseWhenTheHeightDoesNotRespectTheExpectations() {
        // Given
        when(integerPredicates.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(false).thenReturn(true);
        when(nameValidator.test(any(NameDto.class))).thenReturn(true);
        PaddockCreationValidator validator = new PaddockCreationValidator(
                nameValidator,
                integerPredicates,
                RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        PaddockCreationContext result = validator.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

}
