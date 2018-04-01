package doyenm.zooshell.validator;

import doyenm.zooshell.common.FindKeeper;
import doyenm.zooshell.keeper.rename.KeeperRenameContext;
import doyenm.zooshell.keeper.rename.KeeperRenameValidator;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

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

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
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
        NameValidator nameValidator = givenNameTest(true);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(nameValidator,
                findKeeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsKO() {
        // Given
        KeeperRenameContext context = givenContext(Mockito.mock(AnimalKeeper.class));
        NameValidator nameValidator = givenNameTest(false);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(
                nameValidator,
                findKeeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCurrentNameIsNotTheOneOfAKeeper() {
        // Given
        KeeperRenameContext context = givenContext(null);
              NameValidator nameValidator = givenNameTest(true);
        FindKeeper findKeeper = givenFindKeeper();
        KeeperRenameValidator validator = new KeeperRenameValidator(
                nameValidator,
                findKeeper);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
