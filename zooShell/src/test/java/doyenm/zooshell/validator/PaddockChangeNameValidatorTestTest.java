package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
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
public class PaddockChangeNameValidatorTestTest {

    private Position givenPosition() {
        return Mockito.mock(Position.class);
    }

    private Paddock givenPaddockWithEntry(Position entry) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getEntry()).thenReturn(entry);
        return pad;
    }

    private Zoo givenZooWithPaddockAndName(Paddock paddock, String name) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Paddock> map = new HashMap<>();
        map.put(name, paddock);
        Mockito.when(zoo.getPaddocks()).thenReturn(map);
        return zoo;
    }

    private PaddockChangeNameContext givenContextWithZooNewNameAndPaddock(
            Zoo zoo, String name, Paddock paddock) {
        PaddockChangeNameContext context = Mockito.mock(PaddockChangeNameContext.class);
        Mockito.when(context.getNewName()).thenReturn(name);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedPaddock()).thenReturn(paddock);
        Mockito.doCallRealMethod().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        return context;
    }

    /**
     * An paddock can be renamed if : - the  paddock has an entry - the paddock
     * exists - the new name is shorter than 50 characters
     */
    @Test
    public void shouldReturnTrueWhenThePaddockCanBeRenamed() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry);
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithPaddockAndName(convertedPaddock, newName);
        PaddockChangeNameContext context = givenContextWithZooNewNameAndPaddock(
                zoo,
                newName,
                convertedPaddock
        );
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = null;
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithPaddockAndName(convertedPaddock, newName);
        PaddockChangeNameContext context = givenContextWithZooNewNameAndPaddock(
                zoo,
                newName,
                convertedPaddock
        );
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotHaveAnEntry() {
        // Given
        Position entry = null;
        Paddock convertedPaddock = givenPaddockWithEntry(entry);
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithPaddockAndName(convertedPaddock, newName);
        PaddockChangeNameContext context = givenContextWithZooNewNameAndPaddock(
                zoo,
                newName,
                convertedPaddock
        );
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsLongerThan50Characters() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry);
        String newName = TestUtils.generateStringWithLength(51);
        Zoo zoo = givenZooWithPaddockAndName(convertedPaddock, newName);
        PaddockChangeNameContext context = givenContextWithZooNewNameAndPaddock(
                zoo,
                newName,
                convertedPaddock
        );
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
