package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyInt;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysValidatorTestTest {

    private AnimalUpdateFastDaysContext givenContext(Animal animal, Position position, int fastDays) {
        AnimalUpdateFastDaysContext context = Mockito.mock(AnimalUpdateFastDaysContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getEntry()).thenReturn(position);
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedFastDays()).thenReturn(fastDays);
        return context;
    }

    private FindAnimal givenFindAnimal() {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        return mock;
    }

    private IntegerValuePredicates givenIntegerPredicates(boolean value, boolean value2) {
        IntegerValuePredicates mock = Mockito.mock(IntegerValuePredicates.class);
        Mockito.when(mock.mustBeGreaterOrEqualsThan(anyInt(), anyInt())).thenReturn(value);
        Mockito.when(mock.mustBeLowerOrEqualsThan(anyInt(), anyInt())).thenReturn(value2);
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheFastDaysIsBetweenZeroAndSevenAndThePaddockHasAnEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(true, true);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
@Test
    public void shouldReturnTrueWhenTheAnimalExistsTheFastDaysIsBetweenZeroAndThePaddockHasAnEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(true, true);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheFastDaysIsBetweenSevenAndThePaddockHasAnEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(true, true);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenTheFastDaysAreLessThanZero() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(false, true);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheFastDaysAreMoreThanSeven() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(true, false);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(true, true);
        AnimalUpdateFastDaysContext context = givenContext(
                null,
                Mockito.mock(Position.class),
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenThePaddockHasNoEntry() {
        // Given
        FindAnimal findAnimal = givenFindAnimal();
        IntegerValuePredicates integerPredicates = givenIntegerPredicates(false, true);
        AnimalUpdateFastDaysContext context = givenContext(
                Mockito.mock(Animal.class),
                null,
                RandomUtils.nextInt()
        );
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator(findAnimal, integerPredicates);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
