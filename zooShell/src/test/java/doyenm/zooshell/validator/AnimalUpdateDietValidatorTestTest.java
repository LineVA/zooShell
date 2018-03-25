package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietValidatorTestTest {

    private AnimalUpdateDietContext givenContext(Animal animal, Position position, List<Diet> diets, List<String> dietsStr) {
        AnimalUpdateDietContext context = Mockito.mock(AnimalUpdateDietContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getEntry()).thenReturn(position);
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedDiets()).thenReturn(diets);
        Mockito.when(context.getDiets()).thenReturn(dietsStr);
        return context;
    }

    private FindingDietFunction givenFindingDiet() {
        FindingDietFunction mock = Mockito.mock(FindingDietFunction.class);
        FindingDietContext context = Mockito.mock(FindingDietContext.class);
        Mockito.when(context.getConvertedDiet()).thenReturn(null);
        Mockito.when(mock.apply(Mockito.anyObject())).thenReturn(context);
        return mock;
    }

    private FindAnimal givenFindAnimal() {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalTheDietAndTheCorrespondingPaddockExist() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        FindAnimal findAnimal = givenFindAnimal();
        AnimalUpdateDietContext context = givenContext(
                Mockito.mock(Animal.class), 
                Mockito.mock(Position.class),
                new ArrayList<Diet>(),
                new ArrayList<String>());
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

 @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        FindAnimal findAnimal = givenFindAnimal();
        AnimalUpdateDietContext context = givenContext(
                null, 
                Mockito.mock(Position.class),
                new ArrayList<Diet>(),
                new ArrayList<String>());
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsNoEntryInThePaddockOfTheAnimal() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        FindAnimal findAnimal = givenFindAnimal();
        AnimalUpdateDietContext context = givenContext(
                Mockito.mock(Animal.class), 
               null,
                new ArrayList<Diet>(),
                new ArrayList<String>());
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheConvertedDietsAreNull() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        FindAnimal findAnimal = givenFindAnimal();
        AnimalUpdateDietContext context = givenContext(
                Mockito.mock(Animal.class), 
                Mockito.mock(Position.class),
                null, 
                new ArrayList<String>());
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenSomeOfTheRequestedDietsDoNotExist() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        FindAnimal findAnimal = givenFindAnimal();
        AnimalUpdateDietContext context = givenContext(
                Mockito.mock(Animal.class), 
                Mockito.mock(Position.class),
                new ArrayList<Diet>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator(findingDietFunction, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }
}
