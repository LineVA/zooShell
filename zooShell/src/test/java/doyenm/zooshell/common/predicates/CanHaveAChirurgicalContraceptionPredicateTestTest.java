package doyenm.zooshell.common.predicates;

import doyenm.zooshell.animal.contraception.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.ReproductionAttributes;
import doyenm.zooshell.model.Sex;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class CanHaveAChirurgicalContraceptionPredicateTestTest {

    private ReproductionAttributes givenReproductionAttributesWithMaleMaturity(int maturity) {
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getMaleMaturityAge()).thenReturn(maturity);
        return attributes;
    }

    private ReproductionAttributes givenReproductionAttributesWithFemaleMaturity(int maturity) {
        ReproductionAttributes attributes = Mockito.mock(ReproductionAttributes.class);
        Mockito.when(attributes.getFemaleMaturityAge()).thenReturn(maturity);
        return attributes;
    }

    private Animal givenAnimal() {
        Animal animal = Mockito.mock(Animal.class);
        return animal;
    }

    private Animal givenAnimalWithSexAgeAndReproduction(Sex sex, int age, ReproductionAttributes attributes) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getAge()).thenReturn(age);
        Mockito.when(animal.getSex()).thenReturn(sex);
        Mockito.when(animal.getReproductionAttributes()).thenReturn(attributes);
        return animal;
    }

    private AnimalUpdateContraceptionContext givenContextWithAnimalAndContraception(
            Animal animal, ContraceptionMethod method) {
        AnimalUpdateContraceptionContext context = Mockito.mock(AnimalUpdateContraceptionContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getConvertedContraceptionMethod()).thenReturn(method);
        return context;
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsNone() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.NONE);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsFemaleImplant() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_IMPLANT);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsFemalePill() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_PILL);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsMaleImplant() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.MALE_IMPLANT);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenContraceptionIsCastrationTheAnimalIsAnOldEnoughMale() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(20);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.MALE, 15, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.CASTRACTION);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenContraceptionIsCastrationTheAnimalIsNotAnOldEnoughMale() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(15);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.MALE, 1, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.CASTRACTION);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnTrueWhenContraceptionIsSterilizationTheAnimalIsAnOldEnoughFemale() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(20);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 15, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.STERILIZATION);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenContraceptionIsSterilizationTheAnimalIsNotAnOldEnoughFemale() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(15);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 1, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.STERILIZATION);
        CanHaveAChirurgicalContraceptionPredicate predicate = new CanHaveAChirurgicalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
