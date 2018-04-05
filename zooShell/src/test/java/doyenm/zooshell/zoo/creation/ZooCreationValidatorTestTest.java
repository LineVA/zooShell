package doyenm.zooshell.validator;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.zoo.creation.ZooCreationContext;
import doyenm.zooshell.zoo.creation.ZooCreationValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class ZooCreationValidatorTestTest {

    private ZooCreationValidator subject;
    private IntegerValuePredicates integerValuePredicates;
    private NameValidator nameValidator;
    private ZooCreationContext context;

    @Before
    public void setUp() {
        integerValuePredicates = mock(IntegerValuePredicates.class);
        nameValidator = mock(NameValidator.class);
        subject = new ZooCreationValidator(integerValuePredicates, nameValidator,
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());

        context = mock(ZooCreationContext.class);
        when(context.getName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getConvertedHeight()).thenReturn(RandomUtils.nextInt());
        when(context.getConvertedWidth()).thenReturn(RandomUtils.nextInt());
        when(context.getConvertedSpeed()).thenReturn(RandomUtils.nextInt());
        when(context.getConvertedHorizon()).thenReturn(RandomUtils.nextInt());
        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(any());
        context.setErrors(Lists.newArrayList());
        Mockito.doNothing().when(context).convert();
    }

    @Test
    public void shouldReturnNoErrorWhenEverythingIsOk() {
        // Given
        givenIntegerValuePredicates(true, true, true, true, true, true);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();

    }

    @Test
    public void shouldReturnOneErrorWhenTheWidthIsTooLow() {
        // Given
        givenIntegerValuePredicates(false, true, true, true, true, true);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnOneErrorWhenTheHeightIsTooLow() {
        // Given
        givenIntegerValuePredicates(true, false, true, true, true, true);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnOneErrorWhenTheHorizonIsTooLowInAbsoluteValue() {
        // Given
        givenIntegerValuePredicates(true, true, false, true, true, true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(2);
    }

    @Test
    public void shouldReturnOneErrorWhenTheHorizonIsTooLowForThisZoo() {
        // Given
        givenIntegerValuePredicates(true, true, true, false, true, true);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnOneErrorWhenTheSpeedIsTooLow() {
        // Given
        givenIntegerValuePredicates(true, true, true, true, false, true);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnOneErrorWhenTheSpeedIsTooHight() {
        // Given
        givenIntegerValuePredicates(true, true, true, true, true, false);
        givenNameValidator(true);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnOneErrorWhenTheNameIsTooLong() {
        // Given
        givenIntegerValuePredicates(true, true, true, true, true, true);
        givenNameValidator(false);
        // When
        ZooCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    private void givenIntegerValuePredicates(boolean bool1, boolean bool2,
                                             boolean bool3, boolean bool4, boolean bool5, boolean bool6) {
        when(integerValuePredicates.mustBeGreaterOrEqualsThan(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(bool1).thenReturn(bool2).thenReturn(bool3).thenReturn(bool5);
        when(integerValuePredicates.mustBeLowerOrEqualsThan(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(bool4).thenReturn(bool6);
    }

    private void givenNameValidator(boolean bool) {
        when(nameValidator.test(any(NameDto.class)))
                .thenReturn(bool);
    }
}
