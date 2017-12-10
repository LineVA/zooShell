package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.CharacterAttributes;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.ReproductionAttributes;
import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDyingMeasuresUpdateIsDeadByPredationTest {

    private Animal givenAnimal(double factor) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setKiller(Mockito.any(Animal.class));
        when(animal.getKiller()).thenCallRealMethod();
        CharacterAttributes att = mock(CharacterAttributes.class);
        when(att.getCohabitationFactor()).thenReturn(factor);
        when(animal.getCharacterAttributes()).thenReturn(att);
        return animal;
    }

    private Animal givenKiller(double factor, int age, int weaningAge) {
        Animal animal = Mockito.mock(Animal.class);
        CharacterAttributes att = mock(CharacterAttributes.class);
        when(att.getCohabitationFactor()).thenReturn(factor);
        when(animal.getCharacterAttributes()).thenReturn(att);
        ReproductionAttributes reproAtt = mock(ReproductionAttributes.class);
        when(reproAtt.getWeaningAge()).thenReturn(weaningAge);
        when(animal.getReproductionAttributes()).thenReturn(reproAtt);
        when(animal.getAge()).thenReturn(age);
        return animal;
    }

    /**
     * The animal was not drowning : so it was drowningDays = 0 It is not
     * drowning for this turn So, we still have drowningDays = 0
     */
    @Test
    public void shouldReturnTheAnimalWithAKillerWhenAllTheConditionsAreOK() {
        // Given
        Animal animal = givenAnimal(0.0);
        Animal killer = givenKiller(0.9, 20, 10);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(mock(AnimalDyingPredicates.class));
        // When
        Animal actualAnimal = measures.updateIsDeadByPredation(animal, Arrays.asList(killer));
        // Then
        Assertions.assertThat(actualAnimal.getKiller()).isEqualTo(killer);
    }

 @Test
    public void shouldReturnNoKillerWhenTheCohabitationFactorOfThePreyIsHigher() {
        // Given
        Animal animal = givenAnimal(0.9);
        Animal killer = givenKiller(0.0, 20, 10);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(mock(AnimalDyingPredicates.class));
        // When
        Animal actualAnimal = measures.updateIsDeadByPredation(animal, Arrays.asList(killer));
        // Then
        Assertions.assertThat(actualAnimal.getKiller()).isNull();
    }
    
    @Test
    public void shouldReturnNoKillerWhenTheDifferenceOfCohabitationFactorsIsNotEnoughHigh() {
        // Given
        Animal animal = givenAnimal(0.9);
        Animal killer = givenKiller(0.8, 20, 10);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(mock(AnimalDyingPredicates.class));
        // When
        Animal actualAnimal = measures.updateIsDeadByPredation(animal, Arrays.asList(killer));
        // Then
        Assertions.assertThat(actualAnimal.getKiller()).isNull();
    }
    
    @Test
    public void shouldReturnNoKillerWhenTheKillerIsNotYetWeaned() {
        // Given
        Animal animal = givenAnimal(0.9);
        Animal killer = givenKiller(0.4, 10, 20);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(mock(AnimalDyingPredicates.class));
        // When
        Animal actualAnimal = measures.updateIsDeadByPredation(animal, Arrays.asList(killer));
        // Then
        Assertions.assertThat(actualAnimal.getKiller()).isNull();
    }

}
