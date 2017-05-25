package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
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
public class FemaleReproductionPredicateTestTest {

    private ReproductionAttributes givenReproductionAttributesWithFemaleMaturityAndFrequency(
            int maturity, double frequency) {
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getFemaleMaturityAge()).thenReturn(maturity);
        Mockito.when(attributes.getFrequency()).thenReturn(frequency);
        return attributes;
    }

    private Animal givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
            int age, Sex sex, ContraceptionMethod method, ReproductionAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getContraceptionMethod()).thenReturn(method);
        return animal;
    }

    private Zoo givenZooWithSpeed(int speed) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getMonthsPerEvaluation()).thenReturn(speed);
        return zoo;
    }

    private AnimalEvaluationContext givenContextWithZooAndAnimal(Zoo zoo, Animal animal) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenIsFemaleIsOldAndHappyEnoughAndCanReproducte() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIsNotFemale() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.MALE, ContraceptionMethod.NONE, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenIsNotMature() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age + 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenCanNotReproducte() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(age, Sex.FEMALE, 
                ContraceptionMethod.NONE, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenIsUnderFemaleImplant() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.FEMALE_IMPLANT, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenIsUnderFemalePill() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.FEMALE_PILL, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenIsSterilized() {
        // Given
        int age = TestUtils.generateInteger();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.STERILIZATION, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
