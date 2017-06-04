package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
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
    
    private Zoo givenZooWithSpeed(int speed){
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getMonthsPerEvaluation()).thenReturn(speed);
        return zoo;
    }
    
    private AnimalEvaluationContext givenContextWithAnimalAndZoo(Animal animal, Zoo zoo){
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        return context;
    }

    @Test
    public void shouldAddTheNumberOfMonthsPerTurnToTheAgeOfTheAnimal() {
        // Given
        int age = TestUtils.generateInteger();
        Animal animal = givenAnimalWithAge(age);
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
     */
    @Test
    public void shouldLetTheNecessityOfNursingByHumansToFalseWhenTheAnimalDidNotNeedIt(){
        // Given
        
        // When
        // Then
        
    }
}
