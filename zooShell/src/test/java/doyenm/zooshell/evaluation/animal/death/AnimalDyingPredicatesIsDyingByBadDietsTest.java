package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.DietsSpecie;
import doyenm.zooshell.model.Specie;
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
public class AnimalDyingPredicatesIsDyingByBadDietsTest {

    private Specie givenSpecieWithDiets(List<Diet> diets) {
        Specie attributes = Mockito.mock(Specie.class);
        DietsSpecie dietsSpecie = new DietsSpecie(diets);
        Mockito.when(attributes.getDiets()).thenReturn(dietsSpecie);
        return attributes;
    }

    private Animal givenAnimalWithDietsAndSpecie(List<Diet> diets, Specie specie) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getDiets()).thenReturn(diets);
        Mockito.when(animal.getSpecie()).thenReturn(specie);
        return animal;
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalHasAtLeatsOneDietnCommonWithItsSpecie() {
        // Given
        Specie specie = givenSpecieWithDiets(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.NECTARIVOROUS, Diet.CARNIVOROUS), specie);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByBadDiets(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalHasNoDiet() {
       // Given
        Specie specie = givenSpecieWithDiets(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(new ArrayList<>(), specie);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByBadDiets(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenTheSpecieHasNoDiet() {
       // Given
        Specie specie = givenSpecieWithDiets(new ArrayList<>());
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS), specie);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByBadDiets(animal);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalHasNoDietInCommonWithItsSpecie() {
       // Given
        Specie specie = givenSpecieWithDiets(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.NECTARIVOROUS), specie);
        AnimalDyingPredicates predicate = new AnimalDyingPredicates();
        // When
        boolean result = predicate.isDyingByBadDiets(animal);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
