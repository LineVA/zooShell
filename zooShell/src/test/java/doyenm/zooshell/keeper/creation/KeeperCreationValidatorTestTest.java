package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.model.AnimalKeeper;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;

/**
 * @author doyenm
 */
public class KeeperCreationValidatorTestTest {

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
        return mock;
    }

    private NumberOfKeepersPredicates givenKeepersPredicate(boolean value) {
        NumberOfKeepersPredicates mock = Mockito.mock(NumberOfKeepersPredicates.class);
        Mockito.when(mock.test(Mockito.any(KeeperCreationContext.class))).thenReturn(value);
        return mock;
    }

    private PresenceOfAnimalsPredicate givenPresenceOfAnimalsPredicate(boolean value) {
        PresenceOfAnimalsPredicate mock = Mockito.mock(PresenceOfAnimalsPredicate.class);
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
     * A keeper can be created if : - the name is correct
     * - there is at least one more free place
     * - there is at least one animal in the zoo
     */
    @Test
    public void shouldReturnTrueWhenTheKeeperCanBeCreated() {
        // Given
        NameValidator nameValidator = givenNameTest(true);
        NumberOfKeepersPredicates numberPredicates = givenKeepersPredicate(true);
        PresenceOfAnimalsPredicate animalsPredicate = givenPresenceOfAnimalsPredicate(true);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates, animalsPredicate
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
        NumberOfKeepersPredicates numberPredicates = givenKeepersPredicate(true);
        PresenceOfAnimalsPredicate animalsPredicate = givenPresenceOfAnimalsPredicate(true);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates, animalsPredicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoFreePlaceForANewKeeper() {
        // Given
        NameValidator nameValidator = givenNameTest(true);
        NumberOfKeepersPredicates numberPredicates = givenKeepersPredicate(false);
        PresenceOfAnimalsPredicate animalsPredicate = givenPresenceOfAnimalsPredicate(true);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates, animalsPredicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoAnimalInTheZoo() {
        // Given
        NameValidator nameValidator = givenNameTest(true);
        NumberOfKeepersPredicates numberPredicates = givenKeepersPredicate(false);
        PresenceOfAnimalsPredicate animalsPredicate = givenPresenceOfAnimalsPredicate(false);
        String keeperName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator(
                nameValidator, numberPredicates, animalsPredicate);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
