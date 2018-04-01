package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.*;
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
public class AnimalBiomeEvaluationControllerApplyTest {

    private Specie givenSpecieWithBiomesList(List<Biome> biomes) {
        Specie specie = Mockito.mock(Specie.class);
        BiomesSpecie biomesSpecie = new BiomesSpecie(biomes);
        Mockito.when(specie.getBiomes()).thenReturn(biomesSpecie);
        return specie;
    }

    private Paddock givenPaddockWithBiome(Biome biome) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getBiome()).thenReturn(biome);
        return pad;
    }

    private Animal givenAnimalWithPaddockAndSpecie(Paddock pad, Specie specie) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        Mockito.when(animal.getSpecie()).thenReturn(specie);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimal(Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.doCallRealMethod().when(context).setBiomeWellBeing(Mockito.anyDouble());
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getBiomeWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setBiomeWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        return context;
    }

    @Test
    public void shouldSetBiomeWellBeingToZeroWhenTheBiomeOfThePaddockIsNotOneOfTheSpecie() {
        // Given
        Specie specie = givenSpecieWithBiomesList(Arrays.asList(Biome.DRY_BROADLEAF, Biome.FLOODED_GRASSLANDS));
        Paddock paddock = givenPaddockWithBiome(Biome.DESERT);
        Animal animal = givenAnimalWithPaddockAndSpecie(paddock, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalBiomeEvaluationController controller = new AnimalBiomeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getBiomeWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetBiomeWellBeingToZeroWhenThePaddockHasNoBiome() {
        // Given
        Specie specie = givenSpecieWithBiomesList(Arrays.asList(Biome.DRY_BROADLEAF, Biome.FLOODED_GRASSLANDS));
        Paddock paddock = givenPaddockWithBiome(null);
        Animal animal = givenAnimalWithPaddockAndSpecie(paddock, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalBiomeEvaluationController controller = new AnimalBiomeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getBiomeWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetBiomeWellBeingToZeroWhenTheSpecieHasNoBiome() {
        // Given
        Specie specie = givenSpecieWithBiomesList(new ArrayList<Biome>());
        Paddock paddock = givenPaddockWithBiome(Biome.DESERT);
        Animal animal = givenAnimalWithPaddockAndSpecie(paddock, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalBiomeEvaluationController controller = new AnimalBiomeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getBiomeWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetBiomeWellBeingToZeroWhenTheBiomeOfTheSpecieIsNull() {
        // Given
        Specie specie = givenSpecieWithBiomesList(null);
        Paddock paddock = givenPaddockWithBiome(Biome.DESERT);
        Animal animal = givenAnimalWithPaddockAndSpecie(paddock, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalBiomeEvaluationController controller = new AnimalBiomeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getBiomeWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetBiomeWellBeingToFiveWhenTheBiomesOfTheSpecieInterceptTheBiomeOfThePaddock() {
        // Given
        Specie specie = givenSpecieWithBiomesList(Arrays.asList(Biome.DESERT, Biome.DRY_BROADLEAF));
        Paddock paddock = givenPaddockWithBiome(Biome.DESERT);
        Animal animal = givenAnimalWithPaddockAndSpecie(paddock, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalBiomeEvaluationController controller = new AnimalBiomeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getBiomeWellBeing()).isEqualTo(5.0);
    }
}
