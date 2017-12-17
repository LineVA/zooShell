package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.KeepersNumberPredicate;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperCreationValidatorTestTest {

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
        return mock;
    }

    private KeepersNumberPredicate givenKeepersPredicate(boolean value) {
        KeepersNumberPredicate mock = Mockito.mock(KeepersNumberPredicate.class);
        Mockito.when(mock.test(Mockito.any(KeeperCreationContext.class))).thenReturn(value);
        return mock;
    }

    private AnimalKeeper givenKeeper() {
        return Mockito.mock(AnimalKeeper.class);
    }

    private KeeperCreationContext givenContextWithKeeperNameAndKeepers(
            String name, AnimalKeeper keeper) {
        KeeperCreationContext context = Mockito.mock(KeeperCreationContext.class);
        Mockito.when(context.getKeeper()).thenReturn(name);
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(name.toUpperCase(), keeper);
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        return context;
    }

    /**
     * A keeper can be created if : - the name is not empty - the name is
     * shorter than 50 characters - A keeper with this name does not already
     * existing - there is at least one more free place
     */
    @Test
    public void shouldReturnTrueWhenTheKeeperCanBeCreated() {
        // Given
        NameValidator nameValidator = givenNameTest(true);
        KeepersNumberPredicate numberPredicates = givenKeepersPredicate(true);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates
        );
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNameIsIncorrect() {
        // Given
        NameValidator nameValidator = givenNameTest(false);
        KeepersNumberPredicate numberPredicates = givenKeepersPredicate(true);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsFreePlaceForANewKeeper() {
        // Given
        NameValidator nameValidator = givenNameTest(true);
        KeepersNumberPredicate numberPredicates = givenKeepersPredicate(false);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
