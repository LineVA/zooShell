package doyenm.zooshell.evaluation.animal.death;

import doyenm.zooshell.model.Animal;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDyingMeasuresUpdateIsDyingByDrowningTest {

    private AnimalDyingPredicates givenDyingPredicatesWithDrowning(boolean drowning){
        AnimalDyingPredicates animalDyingPredicates = Mockito.mock(AnimalDyingPredicates.class);
        Mockito.when(animalDyingPredicates.isDyingByDrowning(Mockito.any(Animal.class))).thenReturn(drowning);
        return animalDyingPredicates;
    }
    
    private Animal givenAnimalWithDrowningDays(int days){
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setDaysOfDrowning(Mockito.anyInt());
        Mockito.when(animal.getDaysOfDrowning()).thenCallRealMethod();
        animal.setDaysOfDrowning(days);
        return animal;
    }
    
    /**
     * The animal was not drowning : so it was drowningDays = 0
     * It is not drowning for this turn
     * So, we still have drowningDays = 0
     */
    @Test
    public void shouldReturnTheAnimalWithDrowningDaysToZeroWhenItWasNotDrowningAndItIsNot(){
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithDrowning(false);
        Animal animal = givenAnimalWithDrowningDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates, null);
        // When
        Animal actualAnimal = measures.updateIsDyingByDrowning(animal);
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfDrowning()).isEqualTo(0);
    }
    
      /**
     * The animal was not drowning : so it was drowningDays = 0
     * It is drowning for this turn
     * So, we now have drowningDays = 1
     */
    @Test
    public void shouldReturnTheAnimalWithDrowningDaysToOneWhenItWasNotDrowningAndItIsNow(){
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithDrowning(true);
        Animal animal = givenAnimalWithDrowningDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates, null);
        // When
        Animal actualAnimal = measures.updateIsDyingByDrowning(animal);
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfDrowning()).isEqualTo(1);
    }
    
      /**
     * The animal was drowning : so it was drowningDays >= 0 (= 1 for example) 
     * It is drowning for this turn
     * So, we now have drowningDays = 2
     */
    @Test
    public void shouldReturnTheAnimalWithDrowningDaysToTwoWhenItWasDrowningAndItIsStill(){
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithDrowning(true);
        Animal animal = givenAnimalWithDrowningDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates, null);
        // When
        Animal actualAnimal = measures.updateIsDyingByDrowning(animal);
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfDrowning()).isEqualTo(2);
    }
    
      /**
     * The animal was drowning : so it was drowningDays = 1 for example
     * It is NOT drowning for this turn
     * So, we now have drowningDays = 0
     */
    @Test
    public void shouldReturnTheAnimalWithDrowningDaysToZeroWhenItWasDrowningAndItIsNotAnymore(){
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithDrowning(false);
        Animal animal = givenAnimalWithDrowningDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates, null);
        // When
        Animal actualAnimal = measures.updateIsDyingByDrowning(animal);
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfDrowning()).isEqualTo(0);
    }
}
