package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.model.WellBeing;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnimalArrangementsEvaluationControllerApplyTest {

    private AnimalArrangementsEvaluationController subject;

    @Before
    public void setUp() {
        subject = new AnimalArrangementsEvaluationController();
    }

    private Animal givenAnimal() {
        Animal animal = mock(Animal.class);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimal(Animal animal, List<PaddockFacility> paddockFacilities,
                                                           List<PaddockFacility> specieArrangements) {
        AnimalEvaluationContext context = mock(AnimalEvaluationContext.class);
        when(context.getAnimal()).thenReturn(animal);
        WellBeing wb = mock(WellBeing.class);
        when(wb.getInstallationsWellBeing()).thenCallRealMethod();
        doCallRealMethod().when(wb).setInstallationsWellBeing(Mockito.anyDouble());
        when(context.getWellBeingObj()).thenReturn(wb);
        when(context.getInstallationsWellBeing()).thenCallRealMethod();
        when(context.getArrangements()).thenReturn(paddockFacilities);
        when(context.getSpecieArrangements()).thenReturn(specieArrangements);
        return context;
    }


    @Test
    public void shouldSetInstallationsWellBeingToZeroWhenTheInstallationsOfThePaddockIsNoneOfTheSpecie() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility paddockFacility = TestUtils.getPaddockArrangement();
        PaddockFacility specieArrangement = TestUtils.getPaddockArrangementExcluding(paddockFacility);
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(paddockFacility),
                Arrays.asList(specieArrangement)
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(-5.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenTheAnimalHasANullInstallation() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility paddockFacility = TestUtils.getPaddockArrangement();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(paddockFacility),
                null
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenTheAnimalHasAnEmptyInstallationsList() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility paddockFacility = TestUtils.getPaddockArrangement();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(paddockFacility),
                Arrays.asList()
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(-5.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenThePaddockHasAnEmptyInstallationsList() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility specieArrangement = TestUtils.getPaddockArrangement();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(),
                Arrays.asList(specieArrangement)
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenThePaddockHasAnNullInstallationsList() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility specieArrangements = TestUtils.getPaddockArrangement();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                null,
                Arrays.asList(specieArrangements)
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenThePaddockAndTheSpecieHaveAnNullInstallationsList() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility specieArrangements = TestUtils.getPaddockArrangement();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                null,
                null
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToZeroWhenThePaddockAndTheSpecieHaveAnEmptyInstallationsList() {
        // Given
        Animal animal = givenAnimal();
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(),
                Arrays.asList()
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(0.0);
    }

    @Test
    public void shouldSetInstallationWellBeingToMaxWhenThePaddockAndTheSpecieHaveTheSameInstallations() {
        // Given
        Animal animal = givenAnimal();
        PaddockFacility specieArrangements = TestUtils.getPaddockArrangement();
        PaddockFacility paddockArrangements = specieArrangements;
        AnimalEvaluationContext context = givenContextWithAnimal(animal,
                Arrays.asList(paddockArrangements),
                Arrays.asList(specieArrangements)
        );
        // When
        AnimalEvaluationContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getWellBeingObj().getInstallationsWellBeing()).isEqualTo(5.0);
    }
}
