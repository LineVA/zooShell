package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalDeathEvaluationController;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.LifespanAttributes;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalDeathEvaluationControllerApplyTest {

    private LifespanAttributes givenLifespanAttributesWithValues(int female, int male) {
        LifespanAttributes attributes = Mockito.mock(LifespanAttributes.class);
        Mockito.when(attributes.getFemaleLifespan()).thenReturn(female);
        Mockito.when(attributes.getMaleLifespan()).thenReturn(male);
        return attributes;
    }

    private Animal givenAnimalWithSexLifespanAndAge(Sex sex, LifespanAttributes attributes, int age) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getLifespanAttributes()).thenReturn(attributes);
        return animal;
    }
    
     private AnimalEvaluationContext givenContextWithAnimal(Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.isDead()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setDead(Mockito.any(Boolean.class));
        return context;
    }

    @Test
    public void shouldSetDeathToTrueWhenTheAnimalIsOlderThanItsLifespanAndItIsAFemale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.FEMALE, lifespanAttributes, female + 1);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isTrue();
    }
    
    
    @Test
    public void shouldSetDeathToTrueWhenTheAnimalHasTheSameAgeThanItsLifespanAndItIsAFemale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.FEMALE, lifespanAttributes, female);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isTrue();
    }
    
    
    @Test
    public void shouldSetDeathToFalseWhenTheAnimalIsYoungerThanItsLifespanAndItIsAFemale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.FEMALE, lifespanAttributes, female - 1);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isFalse();
    }
    
        @Test
    public void shouldSetDeathToTrueWhenTheAnimalIsOlderThanItsLifespanAndItIsAMale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.MALE, lifespanAttributes, male + 1);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isTrue();
    }
    
    
    @Test
    public void shouldSetDeathToTrueWhenTheAnimalHasTheSameAgeThanItsLifespanAndItIsAMale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.MALE, lifespanAttributes, male);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isTrue();
    }
    
    
    @Test
    public void shouldSetDeathToFalseWhenTheAnimalIsYoungerThanItsLifespanAndItIsAMale() {
        // Given
        int female = TestUtils.generateInteger();
        int male = TestUtils.generateInteger();
        LifespanAttributes lifespanAttributes = givenLifespanAttributesWithValues(female, male);
        Animal animal = givenAnimalWithSexLifespanAndAge(Sex.MALE, lifespanAttributes, male - 1);
        AnimalEvaluationContext context = givenContextWithAnimal(animal);
        AnimalDeathEvaluationController controller = new AnimalDeathEvaluationController();
        // When
        AnimalEvaluationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.isDead()).isFalse();
    }
}
