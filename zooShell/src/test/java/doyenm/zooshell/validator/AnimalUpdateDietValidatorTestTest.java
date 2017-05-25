package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietValidatorTestTest {

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
        Mockito.when(animal.getName()).thenReturn(TestUtils.generateString());
        return animal;
    }

    private Zoo givenZooWithAnimals(Animal animal) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Animal> animals = new HashMap<>();
        animals.put(animal.getName(), animal);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalUpdateDietContext givenContextWithZooAnimalAndDiets(Zoo zoo, Animal animal, List<Diet> diet) {
        AnimalUpdateDietContext context = Mockito.mock(AnimalUpdateDietContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedDiets()).thenReturn(new ArrayList<>()).thenReturn(diet);
        Mockito.when(context.getAnimal()).thenReturn(TestUtils.generateString());
        Mockito.when(context.getDiets()).thenReturn(Arrays.asList(TestUtils.generateString()));
        return context;
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalAndTheDietExistAndTheCorrespondingPaddockHasAnEntry() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Diet diet = Diet.NECTARIVOROUS;
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZooWithAnimals(animal);
        List<Diet> diets = new ArrayList<>();
        diets.add(diet);
        AnimalUpdateDietContext context = givenContextWithZooAnimalAndDiets(zoo, animal, diets);
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalAndTheDietExistAndTheCorrepsondingPaddockHasNoEntry() {
        // Given
        Position position = null;
        Paddock paddock = givenPaddockWithEntry(position);
        Diet diet = Diet.NECTARIVOROUS;
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZooWithAnimals(animal);
        List<Diet> diets = new ArrayList<>();
        diets.add(diet);
        AnimalUpdateDietContext context = givenContextWithZooAnimalAndDiets(zoo, animal, diets);
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalExistsButNotTheDiet() {
        // Given
        Position position = givenPosition();
        Paddock paddock = givenPaddockWithEntry(position);
        Diet diet = null;
        Animal animal = givenAnimalWithPaddock(paddock);
        Zoo zoo = givenZooWithAnimals(animal);
        List<Diet> diets = new ArrayList<>();
        diets.add(diet);
        AnimalUpdateDietContext context = givenContextWithZooAnimalAndDiets(zoo, animal, diets);
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheDietExistsButNotTheAnimal() {
        // Given
        Diet diet = Diet.NECTARIVOROUS;
        Animal animal = null;
        Zoo zoo = givenZooWithAnimals(animal);
        List<Diet> diets = new ArrayList<>();
        diets.add(diet);
        AnimalUpdateDietContext context = givenContextWithZooAnimalAndDiets(zoo, animal, diets);
        AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
