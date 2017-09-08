package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.anyInt;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityValidatorTestTest {

    private AnimalUpdateFoodQuantityContext givenContext(Animal animal, Position position, double quantity) {
        AnimalUpdateFoodQuantityContext context = Mockito.mock(AnimalUpdateFoodQuantityContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getEntry()).thenReturn(position);
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedFoodQuantity()).thenReturn(quantity);
        return context;
    }

    private FindAnimal givenFindAnimal() {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        return mock;
    }

    private DoubleValuesPredicates givenPredicates(boolean value) {
        DoubleValuesPredicates mock = Mockito.mock(DoubleValuesPredicates.class);
        Mockito.when(mock.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(value);
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheQuantoityIsCorrectAndThePaddockHasAnEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        DoubleValuesPredicates doublePredicates = givenPredicates(true);
        AnimalUpdateFoodQuantityContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextDouble()
        );
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator(doublePredicates, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheQuantityIsNotCorrect() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        DoubleValuesPredicates doublePredicates = givenPredicates(false);
        AnimalUpdateFoodQuantityContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextDouble()
        );
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator(doublePredicates, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        DoubleValuesPredicates doublePredicates = givenPredicates(true);
        AnimalUpdateFoodQuantityContext context = givenContext(
                null,
                Mockito.mock(Position.class),
                RandomUtils.nextDouble()
        );
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator(doublePredicates, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThePaddockHasNoEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        DoubleValuesPredicates doublePredicates = givenPredicates(true);
        AnimalUpdateFoodQuantityContext context = givenContext(
                Mockito.mock(Animal.class),
                null,
                RandomUtils.nextDouble()
        );
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator(doublePredicates, findAnimal);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
