package doyenm.zooshell.common.predicates;

import doyenm.zooshell.animal.contraception.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Sex;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class IsContraceptionCompatibleWithSexPredicateTestTest {

    private Animal givenAnimalWithSex(Sex sex) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getSex()).thenReturn(sex);
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
    public void shouldReturnTrueWhenTheContraceptionIsNoneAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.NONE);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsNoneAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.NONE);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsFemaleImplantAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_IMPLANT);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsFemalePillAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_PILL);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsSterilizationAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.STERILIZATION);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheContraceptionIsMaleImplantAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.MALE_IMPLANT);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheContraceptionIsCastrationAndTheAnimalIsAFemale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.FEMALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.CASTRACTION);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheContraceptionIsFemaleImplantAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_IMPLANT);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheContraceptionIsFemalePillAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.FEMALE_PILL);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheContraceptionIsSterilizationAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.STERILIZATION);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsMaleImplantAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.MALE_IMPLANT);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheContraceptionIsCastrationAndTheAnimalIsAMale() {
        // Given
        Animal animal = givenAnimalWithSex(Sex.MALE);
        AnimalUpdateContraceptionContext context = givenContextWithAnimalAndContraception(animal, ContraceptionMethod.CASTRACTION);
        IsContraceptionCompatibleWithSexPredicate predicate = new IsContraceptionCompatibleWithSexPredicate();
        // When
        boolean result = predicate.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
}
