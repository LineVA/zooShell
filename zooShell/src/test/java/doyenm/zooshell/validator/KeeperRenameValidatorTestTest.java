package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperRenameValidatorTestTest {

    private KeeperRenameContext givenContext(AnimalKeeper keeper) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getKeeper()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getNewKeeperName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getConvertedKeeper()).thenReturn(keeper);
        Mockito.when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        return context;
    }

    private StringLengthPredicates givenStringPredicates(boolean bool) {
        StringLengthPredicates mock = Mockito.mock(StringLengthPredicates.class);
        Mockito.when(mock.mustBeLowerOrEqualsThan(Mockito.anyString(), Mockito.anyInt())).thenReturn(bool);
        return mock;
    }

    private UniquenessNamesBiPredicates givenUniquenessPredicates(boolean bool) {
        UniquenessNamesBiPredicates mock = Mockito.mock(UniquenessNamesBiPredicates.class);
        Mockito.when(mock.test(Mockito.anyString(), Mockito.anySet())).thenReturn(bool);
        return mock;
    }

    private FindKeeper givenFindKeeper() {
        FindKeeper mock = Mockito.mock(FindKeeper.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), Mockito.anyString())).thenReturn(Mockito.mock(AnimalKeeper.class));
        return mock;
    }

    /**
     * The new name is correct when : - it is shorter or equals than 50
     * characters - it is not already the name of a keeper - the current name is
     * the one of a keeper
     */
    @Test
    public void shouldReturnTrueWhenTheNewNameIsCorrect() {
        // Given
        KeeperRenameContext context = givenContext(Mockito.mock(AnimalKeeper.class));
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessPredicates(true);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                findKeeper,
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

 @Test
    public void shouldReturnFalseWhenTheNewNameIstooLong() {
        // Given
        KeeperRenameContext context = givenContext(Mockito.mock(AnimalKeeper.class));
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(false);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessPredicates(true);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                findKeeper,
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheNewNameIsAlreadyTheNameOfAKeeper() {
        // Given
        KeeperRenameContext context = givenContext(Mockito.mock(AnimalKeeper.class));
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessPredicates(false);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                findKeeper,
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheCurrentNameIsNotTheOneOfAKeeper() {
        // Given
        KeeperRenameContext context = givenContext(null);
        StringLengthPredicates stringLengthPredicates = givenStringPredicates(true);
        UniquenessNamesBiPredicates uniquenessNamesBiPredicates = givenUniquenessPredicates(true);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(stringLengthPredicates,
                uniquenessNamesBiPredicates,
                findKeeper,
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
