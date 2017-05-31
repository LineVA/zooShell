package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperRenameValidatorTestTest {

    private Map<String, AnimalKeeper> givenMapWithName(String name) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(name, keeper);
        return map;
    }

    private KeeperRenameContext givenContextWithNewNameCurrentNameAndMap(
            String newName, String currentName, Map<String, AnimalKeeper> map) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getKeeper()).thenReturn(currentName);
        Mockito.when(context.getNewKeeperName()).thenReturn(newName);
        Mockito.when(context.getKeepers()).thenReturn(map);
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(context.getConvertedKeeper()).thenReturn(keeper);
        return context;
    }
    
    private KeeperRenameContext givenContextWithNewNameCurrentNameAndMap_NoExistingKeeper(
            String newName, String currentName, Map<String, AnimalKeeper> map) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getKeeper()).thenReturn(currentName);
        Mockito.when(context.getNewKeeperName()).thenReturn(newName);
        Mockito.when(context.getKeepers()).thenReturn(map);
        Mockito.when(context.getConvertedKeeper()).thenReturn(null);
        return context;
    }

    /**
     * The new name is correct when : - it is shorter or equals than 50
     * characters - it is not already the name of a keeper - the current name is
     * the one of a keeper
     */
    @Test
    public void shouldReturnTrueWhenTheNewNameIsCorrect() {
        // Given
        String newName = TestUtils.generateString();
        String currentName = TestUtils.generateString();
        Map<String, AnimalKeeper> map = givenMapWithName(currentName);
        KeeperRenameContext context = givenContextWithNewNameCurrentNameAndMap(newName, currentName, map);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsTooLong() {
        // Given
        String newName = TestUtils.generateStringWithLength(51);
        String currentName = TestUtils.generateString();
        Map<String, AnimalKeeper> map = givenMapWithName(currentName);
        KeeperRenameContext context = givenContextWithNewNameCurrentNameAndMap(newName, currentName, map);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsAlreadyTheNameOfAnExistingKeeper() {
        // Given
        String newName = TestUtils.generateString();
        String currentName = TestUtils.generateString();
        Map<String, AnimalKeeper> map = givenMapWithName(newName);
        KeeperRenameContext context = givenContextWithNewNameCurrentNameAndMap(newName, currentName, map);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(newName).isNotEqualToIgnoringCase(currentName);
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCurrentNameIsNotTheOneOfAnExistingKeeper() {
        // Given
        String newName = TestUtils.generateString();
        String currentName = TestUtils.generateString();
        String existingName = TestUtils.generateString();
        Map<String, AnimalKeeper> map = givenMapWithName(existingName);
        KeeperRenameContext context = givenContextWithNewNameCurrentNameAndMap_NoExistingKeeper(newName, currentName, map);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(newName).isNotEqualToIgnoringCase(currentName);
        Assertions.assertThat(currentName).isNotEqualToIgnoringCase(existingName);
        Assertions.assertThat(result).isFalse();
    }
}
