package doyenm.zooshell.controller.animalcontroller.evaluation.death;

import doyenm.zooshell.model.Animal;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDyingMeasuresUpdateIsDyingByHungerTest {

    private AnimalDyingPredicates givenDyingPredicatesWithHunger(
            boolean fast, boolean quantity, boolean diet, boolean keeper) {
        AnimalDyingPredicates animalDyingPredicates = Mockito.mock(AnimalDyingPredicates.class);
        Mockito.when(animalDyingPredicates.isDyingByBadDiets(Mockito.any(Animal.class))).thenReturn(diet);
        Mockito.when(animalDyingPredicates.isDyingByFast(Mockito.any(Animal.class))).thenReturn(fast);
        Mockito.when(animalDyingPredicates.isDyingByFoodQuantityToZero(Mockito.any(Animal.class))).thenReturn(quantity);
        Mockito.when(animalDyingPredicates.isDyingByLackOfKeeper(Mockito.any(Animal.class), Mockito.anyCollection())).thenReturn(keeper);
        return animalDyingPredicates;
    }

    private Animal givenAnimalWithHungerDays(int days) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setDaysOfHunger(Mockito.anyInt());
        Mockito.when(animal.getDaysOfHunger()).thenCallRealMethod();
        animal.setDaysOfHunger(days);
        return animal;
    }

    /**
     * The animal was not hunger : so it was hungerDays = 0  - It is not
     * hunger for this turn So, we still have hungerDays = 0
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToZeroWhenItWasNotHungerAndItIsNot() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, false, false);
        Animal animal = givenAnimalWithHungerDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(0);
    }
    
     /**
     * The animal was  hunger : so it was hungerDays = 1 for example  - It is not
     * hunger for this turn So, we still have hungerDays = 0
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToZeroWhenItWasHungerAndItIsNotAnymore() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, false, false);
        Animal animal = givenAnimalWithHungerDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(0);
    }
    
     /**
     * The animal was not hunger : so it was hungerDays = 0  - It is 
     * hunger for this turn beacause of fastDays So, we now have hungerDays = 1
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToOneWhenItWasNotHungerAndItIsNowOfFastDays() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(true, false, false, false);
        Animal animal = givenAnimalWithHungerDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(1);
    }
    
      /**
     * The animal was not hunger : so it was hungerDays = 0  - It is 
     * hunger for this turn beacause of quantity So, we now have hungerDays = 1
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToOneWhenItWasNotHungerAndItIsNowOfQuantity() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, true, false, false);
        Animal animal = givenAnimalWithHungerDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(1);
    }
    
      /**
     * The animal was not hunger : so it was hungerDays = 0  - It is 
     * hunger for this turn beacause of bad diets So, we now have hungerDays = 1
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToOneWhenItWasNotHungerAndItIsNowOfBadDiets() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, true, false);
        Animal animal = givenAnimalWithHungerDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(1);
    }
    
      /**
     * The animal was not hunger : so it was hungerDays = 0  - It is 
     * hunger for this turn beacause of lack of keeper So, we now have hungerDays = 1
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToOneWhenItWasNotHungerAndItIsNowOfKeepers() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, false, true);
        Animal animal = givenAnimalWithHungerDays(0);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(1);
    }
    
       /**
     * The animal was hunger : so it was hungerDays = 1  - It is 
     * hunger for this turn beacause of fastDays So, we now have hungerDays = 2
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToTwoWhenItWasHungerAndItIsNowOfFastDays() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(true, false, false, false);
        Animal animal = givenAnimalWithHungerDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(2);
    }
    
      /**
     * The animal was hunger : so it was hungerDays = 1  - It is 
     * hunger for this turn beacause of quantity So, we now have hungerDays = 2
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToTwoWhenItWasHungerAndItIsNowOfQuantity() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, true, false, false);
        Animal animal = givenAnimalWithHungerDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(2);
    }
    
      /**
     * The animal was hunger : so it was hungerDays = 1  - It is 
     * hunger for this turn beacause of bad diets So, we now have hungerDays = 2
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToTwoWhenItWasHungerAndItIsNowOfBadDiets() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, true, false);
        Animal animal = givenAnimalWithHungerDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(2);
    }
    
      /**
     * The animal was hunger : so it was hungerDays = 1  - It is 
     * hunger for this turn beacause of lack of keeper So, we now have hungerDays = 2
     */
    @Test
    public void shouldReturnTheAnimalWithHungerDaysToTwoWhenItWasHungerAndItIsNowOfKeepers() {
        // Given
        AnimalDyingPredicates dyingPredicates = givenDyingPredicatesWithHunger(false, false, false, true);
        Animal animal = givenAnimalWithHungerDays(1);
        AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures(dyingPredicates);
        // When
        Animal actualAnimal = measures.updateIsDyingByHunger(animal, new ArrayList<>());
        // Then
        Assertions.assertThat(actualAnimal.getDaysOfHunger()).isEqualTo(2);
    }
}
