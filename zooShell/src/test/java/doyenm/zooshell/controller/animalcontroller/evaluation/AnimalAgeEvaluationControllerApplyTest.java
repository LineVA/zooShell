package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalAgeEvaluationControllerApplyTest {

    private Animal givenAnimalWithAge(int age) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age).thenCallRealMethod();
        Mockito.doCallRealMethod().when(animal).setAge(Mockito.any(Integer.class));
        return animal;
    }

    private Animal givenAnimalWithAgeMaturityAndNursing(int age, int maturity, boolean nursing) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age).thenCallRealMethod();
        Mockito.doCallRealMethod().when(animal).setAge(Mockito.any(Integer.class));
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getWeaningAge()).thenReturn(maturity);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        Mockito.when(animal.isNeedWeaningByHumans()).thenReturn(nursing);
        return animal;
    }

    private Zoo givenZooWithSpeed(int speed) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getMonthsPerEvaluation()).thenReturn(speed);
        return zoo;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getMonthsPerEvaluation()).thenReturn(TestUtils.generateInteger());
        return zoo;
    }

    private AnimalEvaluationContext givenContextWithAnimalAndZoo(Animal animal, Zoo zoo) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        return context;
    }

    @Test
    public void shouldAddTheNumberOfMonthsPerTurnToTheAgeOfTheAnimal() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAgeMaturityAndNursing(age, age - 1, false);
        int speed = TestUtils.generateInteger();
        Zoo zoo = givenZooWithSpeed(speed);
        AnimalEvaluationContext context = givenContextWithAnimalAndZoo(animal, zoo);
        AnimalAgeEvaluationController controller = new AnimalAgeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getAnimal().getAge()).isEqualTo(age + speed);
    }

    /**
     * The animal did not need to be nursing by humans during the current turn
     * even if it is not yet weaned
     */
    @Test
    public void shouldLetTheNecessityOfNursingByHumansToFalseWhenTheAnimalDidNotNeedItAndIsNotYetWeaned() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAgeMaturityAndNursing(age, age - 1, false);
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithAnimalAndZoo(animal, zoo);
        AnimalAgeEvaluationController controller = new AnimalAgeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getAnimal().isNeedWeaningByHumans()).isFalse();
    }
    
       /**
     * The animal need to be nursing by humans during the current turn
     * beacuse it is weaned, even if it needed it the previous turn
     */
    @Test
    public void shouldSetTheNecessityOfNursingByHumansToTrueWhenTheAnimalIsWeaned() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAgeMaturityAndNursing(age, age - 1, true);
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithAnimalAndZoo(animal, zoo);
        AnimalAgeEvaluationController controller = new AnimalAgeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getAnimal().isNeedWeaningByHumans()).isTrue();
    }
    
       /**
     * The animal did not need to be nursing by humans during the current turn
     * beacuse it is weaned
     */
    @Test
    public void shouldLetTheNecessityOfNursingByHumansToFalseWhenTheAnimalIsWeaned_2() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAgeMaturityAndNursing(age, age + 1, false);
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithAnimalAndZoo(animal, zoo);
        AnimalAgeEvaluationController controller = new AnimalAgeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getAnimal().isNeedWeaningByHumans()).isFalse();
    }
    
       /**
     * The animal did not need to be nursing by humans during the current turn
     * beacuse it is weaned, even if it needed it the previous turn
     */
    @Test
    public void shouldSetTheNecessityOfNursingByHumansToTrueWhenTheAnimalIsWeaned_2() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAgeMaturityAndNursing(age, age + 1, true);
        Zoo zoo = givenZoo();
        AnimalEvaluationContext context = givenContextWithAnimalAndZoo(animal, zoo);
        AnimalAgeEvaluationController controller = new AnimalAgeEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getAnimal().isNeedWeaningByHumans()).isTrue();
    }
}
