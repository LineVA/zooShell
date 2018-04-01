package doyenm.zooshell.validator;

import doyenm.zooshell.keeper.creation.KeeperCreationContext;
import doyenm.zooshell.keeper.creation.KeeperCreationValidator;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.keeper.creation.KeepersNumberPredicate;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;

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
