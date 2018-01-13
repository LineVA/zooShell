package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalChangePaddockValidatorTest {

    private FindAnimal givenFindAnimal(Animal animal) {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), anyString())).thenReturn(animal);
        return mock;
    }

    private FindPaddock givenFindPaddock(Paddock pad) {
        FindPaddock mock = Mockito.mock(FindPaddock.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), anyString())).thenReturn(pad);
        return mock;
    }

     private Paddock givenPaddock(int turns) {
        Paddock mock = mock(Paddock.class);
        when(mock.getTurnsOfUnusableState()).thenReturn(turns);
        return mock;
    }

    
    private AnimalChangePaddockContext givenContext() {
        AnimalChangePaddockContext context = Mockito.mock(AnimalChangePaddockContext.class);
        when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        when(context.getConvertedPaddock()).thenCallRealMethod();
        doCallRealMethod().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        when(context.getConvertedAnimal()).thenReturn(Mockito.mock(Animal.class));
        doNothing().when(context).setConvertedAnimal(Mockito.any(Animal.class));
        return context;
    }

    /**
     * An animal can change its paddock if : - the new paddock exists - the
     * animal exists
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeMoved() {
        // Given
        Animal convertedAnimal = Mockito.mock(Animal.class);
        Paddock convertedPad = givenPaddock(2);
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        FindPaddock findPaddock = givenFindPaddock(convertedPad);
        AnimalChangePaddockContext context = givenContext();
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator(
                findAnimal,
                findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        Animal convertedAnimal = null;
        Paddock convertedPad = Mockito.mock(Paddock.class);
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        FindPaddock findPaddock = givenFindPaddock(convertedPad);
        AnimalChangePaddockContext context = givenContext();
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator(
                findAnimal,
                findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
       @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
           // Given
        Animal convertedAnimal = Mockito.mock(Animal.class);
        Paddock convertedPad = null;
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        FindPaddock findPaddock = givenFindPaddock(convertedPad);
        AnimalChangePaddockContext context = givenContext();
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator(
                findAnimal,
                findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenThePaddockIsUnusableSinceThreeTurns() {
           // Given
        Animal convertedAnimal = Mockito.mock(Animal.class);
        Paddock convertedPad = givenPaddock(3);
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        FindPaddock findPaddock = givenFindPaddock(convertedPad);
        AnimalChangePaddockContext context = givenContext();
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator(
                findAnimal,
                findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenThePaddockIsUnusableSinceMoreThanThreeTurns() {
           // Given
        Animal convertedAnimal = Mockito.mock(Animal.class);
        Paddock convertedPad = givenPaddock(4);
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        FindPaddock findPaddock = givenFindPaddock(convertedPad);
        AnimalChangePaddockContext context = givenContext();
        AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator(
                findAnimal,
                findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
