package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
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
public class AnimalChangeNameValidatorTestTest {

    private Position givenPosition() {
        return Mockito.mock(Position.class);
    }

    private Paddock givenPaddockWithEntry(Position entry) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getEntry()).thenReturn(entry);
        return pad;
    }

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }
    
    private Zoo givenZooWithAnimalAndName(Animal animal, String name){
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Animal> map = new HashMap<>();
        map.put(name, animal);
        Mockito.when(zoo.getAnimals()).thenReturn(map);
        return zoo;
    }

    private AnimalChangeNameContext givenContextWithZooNewNameAndAnimal(
            Zoo zoo, String name, Animal animal) {
        AnimalChangeNameContext context = Mockito.mock(AnimalChangeNameContext.class);
        Mockito.when(context.getNewName()).thenReturn(name);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.doCallRealMethod().when(context).setConvertedAnimal(Mockito.any(Animal.class));
        return context;
    }

    /**
     * An animal can be renamed if : - the new paddock has an entry - the animal
     * exists - the new name is shorter than 50 characters
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeRenamed() {
        // Given
        Position entry = givenPosition();
        Paddock paddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = givenAnimalWithPaddock(paddock);
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithAnimalAndName(convertedAnimal, newName);
        AnimalChangeNameContext context = givenContextWithZooNewNameAndAnimal(
                zoo,
                newName,
                convertedAnimal
        );
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock paddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = null;
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithAnimalAndName(convertedAnimal, newName);
        AnimalChangeNameContext context = givenContextWithZooNewNameAndAnimal(
                zoo,
                newName,
                convertedAnimal
        );
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThePaddockOfTheAnimalDoesNotHaveAnEntry() {
        // Given
        Position entry = null;
        Paddock paddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = givenAnimalWithPaddock(paddock);
        String newName = TestUtils.generateStringWithLength(49);
        Zoo zoo = givenZooWithAnimalAndName(convertedAnimal, newName);
        AnimalChangeNameContext context = givenContextWithZooNewNameAndAnimal(
                zoo,
                newName,
                convertedAnimal
        );
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheNewNameIsLongerThan50Characters() {
        // Given
        Position entry = givenPosition();
        Paddock paddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = givenAnimalWithPaddock(paddock);
        String newName = TestUtils.generateStringWithLength(51);
        Zoo zoo = givenZooWithAnimalAndName(convertedAnimal, newName);
        AnimalChangeNameContext context = givenContextWithZooNewNameAndAnimal(
                zoo,
                newName,
                convertedAnimal
        );
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
