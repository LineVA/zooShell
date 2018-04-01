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
public class AnimalDietsEvaluationControllerApplyTest {

    private Specie givenSpecieWithDietsList(List<Diet> Diets) {
        Specie specie = Mockito.mock(Specie.class);
        DietsSpecie DietsSpecie = new DietsSpecie(Diets);
        Mockito.when(specie.getDiets()).thenReturn(DietsSpecie);
        return specie;
    }

    private Animal givenAnimalWithDietsAndSpecie(List<Diet> diets, Specie specie) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getDiets()).thenReturn(diets);
        Mockito.when(animal.getSpecie()).thenReturn(specie);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimal(Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        WellBeing wb = Mockito.mock(WellBeing.class);
        Mockito.when(wb.getDietsWellBeing()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(wb).setDietsWellBeing(Mockito.anyDouble());
        Mockito.when(context.getWellBeingObj()).thenReturn(wb);
        Mockito.when(context.getDietsWellBeing()).thenCallRealMethod();
//        Mockito.when(context.BASE).thenCallRealMethod();
        return context;
    }

    @Test
    public void shouldSetDietWellBeingToZeroWhenTheDietsOfTheAnimalIsNotOneOfTheSpecie() {
        // Given
        Specie specie = givenSpecieWithDietsList(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.NECTARIVOROUS), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetDietWellBeingToZeroWhenTheAnimalHasANullDiet() {
        // Given
        Specie specie = givenSpecieWithDietsList(Arrays.asList(Diet.CARNIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(null, specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetDietWellBeingToZeroWhenTheAnimalHasAnEmptyDietsLisy() {
        // Given
        Specie specie = givenSpecieWithDietsList(Arrays.asList(Diet.CARNIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(new ArrayList<Diet>(), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetDietWellBeingToZeroWhenTheSpecieHasNoDiet() {
        // Given
        Specie specie = givenSpecieWithDietsList(new ArrayList<Diet>());
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.CARNIVOROUS), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetDietWellBeingToZeroWhenTheDietOfTheSpecieIsNull() {
        // Given
        Specie specie = givenSpecieWithDietsList(null);
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.CARNIVOROUS), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetDietWellBeingToOneHalfWhenThereIsOneDietInCommonAmongFour() {
        // Given
        Specie specie = givenSpecieWithDietsList(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS, Diet.NONE, Diet.NECTARIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(2.5);
    }

    @Test
    public void shouldSetDietWellBeingToFiveWhenThereAllTheDietsAreInCommon() {
        // Given
        Specie specie = givenSpecieWithDietsList(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS));
        Animal animal = givenAnimalWithDietsAndSpecie(Arrays.asList(Diet.CARNIVOROUS, Diet.FOLIVOROUS), specie);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDietsEvaluationController controller = new AnimalDietsEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getDietsWellBeing()).isEqualTo(5.0);
    }
}