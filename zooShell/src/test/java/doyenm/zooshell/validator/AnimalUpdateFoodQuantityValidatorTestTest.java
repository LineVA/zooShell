package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityValidatorTestTest {

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

    private AnimalUpdateFoodQuantityContext givenContextWithZooAnimalAndFoodQuantity(Zoo zoo, Animal animal, double quantity) {
        AnimalUpdateFoodQuantityContext context = Mockito.mock(AnimalUpdateFoodQuantityContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedFoodQuantity()).thenReturn(quantity);
        Mockito.when(context.getAnimal()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getFoodQuantity()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        return context;
    }

    private AnimalUpdateFoodQuantityContext givenContextWithZooAnimalAndBothFoodQuantity(
            Zoo zoo, Animal animal, double quantity, String str) {
        AnimalUpdateFoodQuantityContext context = Mockito.mock(AnimalUpdateFoodQuantityContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedFoodQuantity()).thenReturn(quantity);
        Mockito.when(context.getAnimal()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getFoodQuantity()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        return context;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalExistsTheQuantityIsGreaterThanZeroAndTheCorrepsondingPaddockHasAnEntry() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZoo();
        double quantity = 10.0;
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, quantity);
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
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
        double quantity = 0.0;
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, quantity);
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
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
        double quantity = TestUtils.generateDouble();
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, quantity);
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
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
        double quantity = -6.0;
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, quantity);
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
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
        double quantity = TestUtils.generateDouble();
        AnimalUpdateFoodQuantityContext context = givenContextWithZooAnimalAndFoodQuantity(zoo, animal, quantity);
        AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
