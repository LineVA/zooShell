package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysValidatorTestTest {

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

    private Zoo givenZoo() {
        return Mockito.mock(Zoo.class);
    }

    private AnimalUpdateFastDaysContext givenContextWithZooAnimalAndFastDays(Zoo zoo, Animal animal, int fast) {
        AnimalUpdateFastDaysContext context = Mockito.mock(AnimalUpdateFastDaysContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedFastDays()).thenReturn(fast);
        Mockito.when(context.getAnimal()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getFastDays()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        return context;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheFastDaysIsBetweenZeroAndSevenAndTheCorrepsondingPaddockHasAnEntry() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = 3;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheQuantityIsZeroAndTheCorrepsondingPaddockHasAnEntry() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = 0;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheQuantityIsSevenAndTheCorrepsondingPaddockHasAnEntry() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = 7;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalExistsTheFoodQuantityIsGreaterOrEqualsThanZeroButTheCorrepsondingPaddockHasNoEntry() {
        // Given
        Position position = null;
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = 7;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFoodQuantityIsLowerThanZero() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = -1;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheFoodQuantityIsGreaterThanZero() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        int fast = 8;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheFoodQuantityisCorretButTheAnimalDoesNotExist() {
        // Given
        Animal animal = null;
        Zoo zoo = givenZoo();
         int fast = 3;
        AnimalUpdateFastDaysContext context = givenContextWithZooAnimalAndFastDays(zoo, animal, fast);
        AnimalUpdateFastDaysValidator validator = new AnimalUpdateFastDaysValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
