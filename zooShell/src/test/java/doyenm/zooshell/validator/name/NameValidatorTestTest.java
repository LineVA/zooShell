/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.validator.name;

import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

import static org.mockito.Matchers.*;

/**
 *
 * @author doyenm
 */
public class NameValidatorTestTest {

    private NameValidator subject;

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

    @Test
    public void shouldReturnTrueWhenTheNameDoesNotAlreadyExistAndIsShorterThanMax() {
        // Given
        StringLengthPredicates lengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessPredicates = givenUniquenessNames(true);
        subject = new NameValidator(lengthPredicates, uniquenessPredicates, RandomUtils.nextInt());
        // When
        boolean result = subject.test(NameDto.builder()
                .testing(RandomStringUtils.randomAlphanumeric(10))
                .existingNames(new HashSet<>())
                .build());
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheNameAlreadyExists() {
        // Given
        StringLengthPredicates lengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessPredicates = givenUniquenessNames(false);
        subject = new NameValidator(lengthPredicates, uniquenessPredicates, RandomUtils.nextInt());
        // When
        boolean result = subject.test(NameDto.builder()
                .testing(RandomStringUtils.randomAlphanumeric(10))
                .existingNames(new HashSet<>())
                .build());
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheNameIsTooLong() {
        // Given
        StringLengthPredicates lengthPredicates = givenStringPredicates(false);
        UniquenessNamesBiPredicates uniquenessPredicates = givenUniquenessNames(true);
        subject = new NameValidator(lengthPredicates, uniquenessPredicates, RandomUtils.nextInt());
        // When
        boolean result = subject.test(NameDto.builder()
                .testing(RandomStringUtils.randomAlphanumeric(10))
                .existingNames(new HashSet<>())
                .build());
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheNameIsTooLongAndAlreadyExists() {
        // Given
        StringLengthPredicates lengthPredicates = givenStringPredicates(false);
        UniquenessNamesBiPredicates uniquenessPredicates = givenUniquenessNames(false);
        subject = new NameValidator(lengthPredicates, uniquenessPredicates, RandomUtils.nextInt());
        // When
        boolean result = subject.test(NameDto.builder()
                .testing(RandomStringUtils.randomAlphanumeric(10))
                .existingNames(new HashSet<>())
                .build());
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
