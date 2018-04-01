package doyenm.zooshell.evaluation.animal.reproduction;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.*;
import org.apache.commons.lang.math.RandomUtils;
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

    private Animal givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
            int age, Sex sex, ContraceptionMethod method, int months, ReproductionAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getContraceptionMethod()).thenReturn(method);
        Mockito.when(animal.getMonthsOfGestation()).thenReturn(months);
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
    public void shouldReturnTrueWhenIsFemaleIsOldAndHappyEnoughAndCanReproducteAndNotAlreadyGestating() {
        // Given
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIsAlreadyGestating() {
        // Given
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 4, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenIsNotFemale() {
        // Given
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.MALE, ContraceptionMethod.NONE, 0, attributes);
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
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age + 1, 1.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
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
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
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
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
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
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
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
        int age = RandomUtils.nextInt();
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturityAndFrequency(age - 1, 0.0);
        Animal animal = givenAnimalWithAgeSexContraceptionGestationAndReproductionAttributes(
                age, Sex.FEMALE, ContraceptionMethod.NONE, 0, attributes);
        Zoo zoo = givenZooWithSpeed(12);
        AnimalEvaluationContext context = givenContextWithZooAndAnimal(zoo, animal);
        FemaleReproductionPredicate predicate = new FemaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
