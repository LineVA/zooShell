package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
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

    private KeeperRenameContext givenContextWithZooNewNameAndCurrentName(Zoo zoo,
            String newName, String currentName) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getKeeper()).thenReturn(currentName);
        Mockito.when(context.getNewKeeperName()).thenReturn(newName);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(context.getConvertedKeeper()).thenReturn(keeper);
        return context;
    }

    private Zoo givenZooWithMap(Map<String, AnimalKeeper> map) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getKeepers()).thenReturn(map);
        return zoo;
    }

    private KeeperRenameContext givenContextWithZooNewNameAndCurrentName_NoKeeper(
            Zoo zoo, String newName, String currentName) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getKeeper()).thenReturn(currentName);
        Mockito.when(context.getNewKeeperName()).thenReturn(newName);
        Mockito.when(context.getZoo()).thenReturn(zoo);
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
        String newName = RandomStringUtils.randomAlphabetic(10);
        String currentName = RandomStringUtils.randomAlphabetic(10);
        Map<String, AnimalKeeper> map = givenMapWithName(currentName);
        Zoo zoo = givenZooWithMap(map);
        KeeperRenameContext context = givenContextWithZooNewNameAndCurrentName(zoo, newName, currentName);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsTooLong() {
        // Given
        String newName =  RandomStringUtils.randomAlphabetic(51);
        String currentName = RandomStringUtils.randomAlphabetic(10);
        Map<String, AnimalKeeper> map = givenMapWithName(currentName);
        Zoo zoo = givenZooWithMap(map);
        KeeperRenameContext context = givenContextWithZooNewNameAndCurrentName(zoo, newName, currentName);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsAlreadyTheNameOfAnExistingKeeper() {
        // Given
        String newName = RandomStringUtils.randomAlphabetic(10);
        String currentName = RandomStringUtils.randomAlphabetic(10);
        Map<String, AnimalKeeper> map = givenMapWithName(newName);
        Zoo zoo = givenZooWithMap(map);
        KeeperRenameContext context = givenContextWithZooNewNameAndCurrentName(zoo, newName, currentName);
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
        String newName = RandomStringUtils.randomAlphabetic(10);
        String currentName = RandomStringUtils.randomAlphabetic(10);
        String existingName = RandomStringUtils.randomAlphabetic(10);
        Map<String, AnimalKeeper> map = givenMapWithName(existingName);
        Zoo zoo = givenZooWithMap(map);
        KeeperRenameContext context = givenContextWithZooNewNameAndCurrentName_NoKeeper(zoo, newName, currentName);
        KeeperRenameValidator validator = new KeeperRenameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(newName).isNotEqualToIgnoringCase(currentName);
        Assertions.assertThat(currentName).isNotEqualToIgnoringCase(existingName);
        Assertions.assertThat(result).isFalse();
    }
}
