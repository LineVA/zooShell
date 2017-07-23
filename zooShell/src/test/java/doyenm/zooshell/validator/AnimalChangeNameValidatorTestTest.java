package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.HashSet;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameValidatorTestTest {

    private FindAnimal givenFindAnimal(Animal animal) {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), anyString())).thenReturn(animal);
        return mock;
    }

    private StringLengthPredicates givenStringPredicates(boolean value) {
        StringLengthPredicates mock = Mockito.mock(StringLengthPredicates.class);
        Mockito.when(mock.mustBeLowerOrEqualsThan(anyString(), anyInt())).thenReturn(value);
        return mock;
    }

    private UniquenessNamesBiPredicates givenUniquenessNames(boolean value) {
        UniquenessNamesBiPredicates mock = Mockito.mock(UniquenessNamesBiPredicates.class);
        Mockito.when(mock.test(anyString(), anySet())).thenReturn(value);
        return mock;
    }

    private Animal givenAnimal() {
        Animal animal = Mockito.mock(Animal.class);
        return animal;
    }

    private AnimalChangeNameContext givenContext() {
        AnimalChangeNameContext context = Mockito.mock(AnimalChangeNameContext.class);
        Mockito.when(context.getNewName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(context.getCurrentName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        Mockito.when(context.getAnimals()).thenReturn(new HashSet<>());
        Mockito.doCallRealMethod().when(context).setConvertedAnimal(Mockito.any(Animal.class));
        return context;
    }

    /**
     * An animal can be renamed if : - the new paddock has an entry - the animal
     * exists - the new name is shorter than 50 characters - the new name is
     * unique
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeRenamed() {
        // Given
        Animal convertedAnimal = givenAnimal();
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        UniquenessNamesBiPredicates namePredicates = givenUniquenessNames(true);
        StringLengthPredicates stringPredicates = givenStringPredicates(true);
        AnimalChangeNameContext context = givenContext();
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator(
                findAnimal,
                stringPredicates,
                namePredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

     @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
      // Given
        Animal convertedAnimal = null;
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        UniquenessNamesBiPredicates namePredicates = givenUniquenessNames(true);
        StringLengthPredicates stringPredicates = givenStringPredicates(true);
        AnimalChangeNameContext context = givenContext();
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator(
                findAnimal,
                stringPredicates,
                namePredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
 
    @Test
    public void shouldReturnFalseWhenTheNewNameIsLongerThan50Characters() {
       // Given
        Animal convertedAnimal = givenAnimal();
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        UniquenessNamesBiPredicates namePredicates = givenUniquenessNames(true);
        StringLengthPredicates stringPredicates = givenStringPredicates(false);
        AnimalChangeNameContext context = givenContext();
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator(
                findAnimal,
                stringPredicates,
                namePredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheNewNameIsAlreadyPresent() {
       // Given
        Animal convertedAnimal = givenAnimal();
        FindAnimal findAnimal = givenFindAnimal(convertedAnimal);
        UniquenessNamesBiPredicates namePredicates = givenUniquenessNames(false);
        StringLengthPredicates stringPredicates = givenStringPredicates(true);
        AnimalChangeNameContext context = givenContext();
        AnimalChangeNameValidator validator = new AnimalChangeNameValidator(
                findAnimal,
                stringPredicates,
                namePredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
