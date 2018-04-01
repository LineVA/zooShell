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
public class CanHaveAHormonalContraceptionPredicateTestTest {

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
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsSterilization() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.STERILIZATION);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionIsCastration() {
        // Given
        Animal animal = givenAnimal();
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.CASTRACTION);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturntrueWhenContraceptionMaleImplantTheAnimalAMaleSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.MALE, 10, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.MALE_IMPLANT);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenContraceptionMaleImplantTheAnimalAMaleNotSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithMaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.MALE, 0, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.MALE_IMPLANT);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturntrueWhenContraceptionFemaleImplantTheAnimalAFemaleSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 10, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_IMPLANT);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenContraceptionFemaleImplantTheAnimalAFemaleNotSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 0, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_IMPLANT);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturntrueWhenContraceptionFemalePillTheAnimalAFemaleSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 10, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_PILL);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnFalseWhenContraceptionFemalePillTheAnimalAFemaleNotSexuallyMature() {
        // Given
        ReproductionAttributes attributes = givenReproductionAttributesWithFemaleMaturity(5);
        Animal animal = givenAnimalWithSexAgeAndReproduction(Sex.FEMALE, 0, attributes);
        AnimalUpdateContraceptionContext context
                = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_PILL);
        CanHaveAHormonalContraceptionPredicate predicate = new CanHaveAHormonalContraceptionPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
}
