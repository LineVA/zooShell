package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalChangePaddockValidatorTest {

    private Specie givenSpecie() {
        return Mockito.mock(Specie.class);
    }

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

    private AnimalChangePaddockContext givenContextWithConvertedPaddockAnimal(
            Paddock pad, Animal animal) {
        AnimalChangePaddockContext context = Mockito.mock(AnimalChangePaddockContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Mockito.doCallRealMethod().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.doCallRealMethod().when(context).setConvertedAnimal(Mockito.any(Animal.class));
        Mockito.when(context.getPaddock()).thenReturn(TestUtils.generateString());
        Mockito.when(context.getAnimalsMap()).thenReturn(new HashMap<>());
        Mockito.when(context.getPaddocksMap()).thenReturn(new HashMap<>());
        return context;
    }

    /**
     * An animal can change its paddock if : - the new paddock exists - the new
     * paddock has an entry - the animal exists
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanMoved() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalChangePaddockContext context = givenContextWithConvertedPaddockAnimal(
                convertedPaddock,
                convertedAnimal
        );
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry);
        Animal convertedAnimal = null;
        AnimalChangePaddockContext context = givenContextWithConvertedPaddockAnimal(
                convertedPaddock,
                convertedAnimal
        );
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
       @Test
    public void shouldReturnFalseWhenThePAddockDoesNotExist() {
        // Given
        Position entry = givenPosition();
        Paddock convertedPaddock = null;
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalChangePaddockContext context = givenContextWithConvertedPaddockAnimal(
                convertedPaddock,
                convertedAnimal
        );
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator();
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
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalChangePaddockContext context = givenContextWithConvertedPaddockAnimal(
                convertedPaddock,
                convertedAnimal
        );
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}