package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class MaleReproductionPredicaateTest {

    private ReproductionAttributes givenReproductionAttributesWithMaleMaturity(
            int maturity) {
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getMaleMaturityAge()).thenReturn(maturity);
        return attributes;
    }

    private Animal givenAnimalWithSpecieAndPaddock(Specie specie, Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getSpecie()).thenReturn(specie);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getName()).thenReturn(RandomStringUtils.random(10));
        return pad;
    }

    private Specie givenSpecie() {
        return Mockito.mock(Specie.class);
    }

    private Animal givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
            int age, Sex sex, ContraceptionMethod method, ReproductionAttributes attributes, Specie specie, Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getSpecie()).thenReturn(specie);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        Mockito.when(animal.getContraceptionMethod()).thenReturn(method);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        return animal;
    }

    private AnimalEvaluationContext givenContextWithAnimalAndAnimalsOfTheZoo(Animal animal, List<Animal> animals) {
        AnimalEvaluationContext context = Mockito.mock(AnimalEvaluationContext.class);
        Mockito.when(context.getAnimal()).thenReturn(animal);
        Mockito.when(context.getAnimalsOfTheZoo()).thenReturn(animals);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenThereIsMaleOldAndHappyEnoughOfTheSameSpecieAndPaddock() {
        // Given
        int age = RandomUtils.nextInt();
        Specie specie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(specie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.NONE, attributes, specie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoMale() {
        // Given
        int age = RandomUtils.nextInt();
        Specie specie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(specie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.FEMALE, ContraceptionMethod.NONE, attributes, specie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoEnoughOldMale() {
        // Given
        int age = RandomUtils.nextInt();
        Specie specie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(specie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age + 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.NONE, attributes, specie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoMaleIsTheSamePaddock() {
        // Given
        int age = RandomUtils.nextInt();
        Specie specie = givenSpecie();
        Paddock femalePaddock = givenPaddock();
        Paddock malePaddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(specie, femalePaddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.NONE, attributes, specie, malePaddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoMaleOfTheSameSpecie() {
        // Given
        int age = RandomUtils.nextInt();
        Specie femaleSpecie = givenSpecie();
        Specie maleSpecie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(femaleSpecie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.NONE, attributes, maleSpecie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheMaleIsCastred() {
        // Given
        int age = RandomUtils.nextInt();
        Specie femaleSpecie = givenSpecie();
        Specie maleSpecie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(femaleSpecie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.CASTRACTION, attributes, maleSpecie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenTheMaleIsUnderImplant() {
        // Given
        int age = RandomUtils.nextInt();
        Specie femaleSpecie = givenSpecie();
        Specie maleSpecie = givenSpecie();
        Paddock paddock = givenPaddock();
        Animal female = givenAnimalWithSpecieAndPaddock(femaleSpecie, paddock);
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(age - 1);
        Animal male = givenAnimalWithAgeSexContraceptionReproductionAttributesSpecieAndPaddock(
                age, Sex.MALE, ContraceptionMethod.MALE_IMPLANT, attributes, maleSpecie, paddock);
        AnimalEvaluationContext context = givenContextWithAnimalAndAnimalsOfTheZoo(female, Arrays.asList(male));
        MaleReproductionPredicate predicate = new MaleReproductionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
