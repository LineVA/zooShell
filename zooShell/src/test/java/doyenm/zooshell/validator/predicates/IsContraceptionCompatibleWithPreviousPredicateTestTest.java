package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class IsContraceptionCompatibleWithPreviousPredicateTestTest {

    private Animal givenAnimalWithContraception(ContraceptionMethod method) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getContraceptionMethod()).thenReturn(method);
        return animal;
    }

    private AnimalUpdateContraceptionContext givenContextWithAnimal(
            Animal animal) {
        AnimalUpdateContraceptionContext context = Mockito.mock(AnimalUpdateContraceptionContext.class);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenThePreviousContraceptionWasNone() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.NONE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnFalseWhenThePreviousContraceptionWasCastration() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.CASTRACTION);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
       @Test
    public void shouldReturnTrueWhenThePreviousContraceptionWasFemaleImplant() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.FEMALE_IMPLANT);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnTrueWhenThePreviousContraceptionWasFemalePill() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.FEMALE_PILL);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnTrueWhenThePreviousContraceptionWasMaleImplant() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.MALE_IMPLANT);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
       @Test
    public void shouldReturnFalseWhenThePreviousContraceptionWasSterilization() {
        // Given
        Animal animal = givenAnimalWithContraception(ContraceptionMethod.STERILIZATION);
        AnimalUpdateContraceptionContext context = givenContextWithAnimal(animal);
        IsContraceptionCompatibleWithPreviousPredicate predicate = new IsContraceptionCompatibleWithPreviousPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
