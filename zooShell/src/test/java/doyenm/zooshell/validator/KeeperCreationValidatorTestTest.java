package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
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
public class KeeperCreationValidatorTestTest {
    
    private AnimalKeeper givenKeeper(){
        return Mockito.mock(AnimalKeeper.class);
    }

    private KeeperCreationContext givenContextWithKeeperName(
             String name) {
        KeeperCreationContext context = Mockito.mock(KeeperCreationContext.class);
        Mockito.when(context.getKeeper()).thenReturn(name);
        Mockito.when(context.getKeepers()).thenReturn(new HashMap<>());
        return context;
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
     * shorter than 50 characters  - A keeper with this name does not already existing
     */
    @Test
    public void shouldReturnTrueWhenTheKeeperCanBeCreated() {
        // Given
        String keeperName = TestUtils.generateString();
        KeeperCreationContext context = givenContextWithKeeperName(
                keeperName
        );
        KeeperCreationValidator validator = new KeeperCreationValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
        @Test
    public void shouldReturnFalseWhenTheNameIsLongerThan50Characters() {
        // Given
        String keeperName = TestUtils.generateStringWithLength(51);
        KeeperCreationContext context = givenContextWithKeeperName(
                keeperName
        );
        KeeperCreationValidator validator = new KeeperCreationValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheNameIsEmpty() {
        // Given
        String keeperName = "  ";
        KeeperCreationContext context = givenContextWithKeeperName(
                keeperName
        );
        KeeperCreationValidator validator = new KeeperCreationValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThereIsAlreadyAKeeperWithThisName(){
        // Given
        String keeperName = TestUtils.generateString();
        AnimalKeeper keeper = givenKeeper();
        KeeperCreationContext context = givenContextWithKeeperNameAndKeepers(
                keeperName, keeper
        );
        KeeperCreationValidator validator = new KeeperCreationValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
