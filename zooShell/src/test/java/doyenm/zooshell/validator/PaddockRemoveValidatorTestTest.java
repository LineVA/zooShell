package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveValidatorTestTest {

    private Paddock givenPaddockWithName(String name) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getName()).thenReturn(name);
        return pad;
    }

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private PaddockContext givenContextWithZooNameAnimalAndConvertedPaddock(
            Zoo zoo, String name, Animal animal, Paddock pad) {
        PaddockContext context = Mockito.mock(PaddockContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Collection coll = new HashSet<Animal>();
        coll.add(animal);
        Mockito.when(context.getAnimals()).thenReturn(coll);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getPaddock()).thenReturn(name);
        return context;
    }

    private Zoo givenZooWithPaddock(Paddock pad) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Paddock> paddocks = new HashMap<>();
        paddocks.put(pad.getName(), pad);
        Mockito.when(zoo.getPaddocks()).thenReturn(paddocks);
        return zoo;
    }

    @Test
    public void shouldReturnTrueIfThePaddockExistsAndIsEmpty() {
        // Given
        String name = TestUtils.generateString();
        Paddock pad = givenPaddockWithName(name);
        Paddock pad2 = givenPaddockWithName(name);
        Animal animal = givenAnimalWithPaddock(pad2);
        Zoo zoo = givenZooWithPaddock(pad);
        PaddockContext context = givenContextWithZooNameAnimalAndConvertedPaddock(zoo, name, animal, pad);
        PaddockRemoveValidator validator = new PaddockRemoveValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseIfThePaddockDoesNotExist() {
        // Given
        String name = TestUtils.generateString();
        Paddock pad = givenPaddockWithName(name);
        Paddock pad2 = givenPaddockWithName(name);
        Animal animal = givenAnimalWithPaddock(pad2);
        Zoo zoo = givenZooWithPaddock(pad);
        PaddockContext context = givenContextWithZooNameAnimalAndConvertedPaddock(zoo, name, animal, pad2);
        PaddockRemoveValidator validator = new PaddockRemoveValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseIfThePaddockExistsButIsNotEmpty() {
        // Given
        String name = TestUtils.generateString();
        Paddock pad = givenPaddockWithName(name);
        Paddock pad2 = givenPaddockWithName(name);
        Animal animal = givenAnimalWithPaddock(pad);
        Zoo zoo = givenZooWithPaddock(pad);
        PaddockContext context = givenContextWithZooNameAnimalAndConvertedPaddock(zoo, name, animal, pad);
        PaddockRemoveValidator validator = new PaddockRemoveValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
