package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingContraceptionContext;
import doyenm.zooshell.validator.function.FindingContraceptionFunction;
import doyenm.zooshell.validator.predicates.CanHaveAChirurgicalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.CanHaveAHormonalContraceptionPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithPreviousPredicate;
import doyenm.zooshell.validator.predicates.IsContraceptionCompatibleWithSexPredicate;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionValidatorApplyTest {

    private FindingContraceptionFunction givenFindingContraception(ContraceptionMethod value) {
        FindingContraceptionFunction mock = Mockito.mock(FindingContraceptionFunction.class);
        FindingContraceptionContext context = Mockito.mock(FindingContraceptionContext.class);
        Mockito.doNothing().when(context).setConvertedContraception(Mockito.any(ContraceptionMethod.class));
        Mockito.when(context.getConvertedContraception()).thenReturn(value);
        Mockito.when(mock.apply(Mockito.any(FindingContraceptionContext.class))).thenReturn(context);
        return mock;
    }

    private FindAnimal givenFindAnimal(Animal value) {
        FindAnimal mock = Mockito.mock(FindAnimal.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), Mockito.anyString())).thenReturn(value);
        return mock;
    }

    private CanHaveAHormonalContraceptionPredicate givenHormonal(boolean value) {
        CanHaveAHormonalContraceptionPredicate mock = Mockito.mock(CanHaveAHormonalContraceptionPredicate.class);
        Mockito.when(mock.test(Mockito.any(AnimalUpdateContraceptionContext.class))).thenReturn(value);
        return mock;
    }

    private CanHaveAChirurgicalContraceptionPredicate givenChirurgical(boolean value) {
        CanHaveAChirurgicalContraceptionPredicate mock = Mockito.mock(CanHaveAChirurgicalContraceptionPredicate.class);
        Mockito.when(mock.test(Mockito.any(AnimalUpdateContraceptionContext.class))).thenReturn(value);
        return mock;
    }

    private IsContraceptionCompatibleWithPreviousPredicate givenPrevious(boolean value) {
        IsContraceptionCompatibleWithPreviousPredicate mock = Mockito.mock(IsContraceptionCompatibleWithPreviousPredicate.class);
        Mockito.when(mock.test(Mockito.any(AnimalUpdateContraceptionContext.class))).thenReturn(value);
        return mock;
    }

    private IsContraceptionCompatibleWithSexPredicate givenSex(boolean value) {
        IsContraceptionCompatibleWithSexPredicate mock = Mockito.mock(IsContraceptionCompatibleWithSexPredicate.class);
        Mockito.when(mock.test(Mockito.any(AnimalUpdateContraceptionContext.class))).thenReturn(value);
        return mock;
    }

    private AnimalUpdateContraceptionContext givenContext() {
        AnimalUpdateContraceptionContext context = Mockito.mock(AnimalUpdateContraceptionContext.class);
        Mockito.doCallRealMethod().when(context).setConvertedAnimal(Mockito.any(Animal.class));
        Mockito.doCallRealMethod().when(context).setConvertedContraceptionMethod(Mockito.any(ContraceptionMethod.class));
        Mockito.when(context.getConvertedAnimal()).thenCallRealMethod();
        Mockito.when(context.getConvertedContraceptionMethod()).thenCallRealMethod();
        Mockito.when(context.getAnimals()).thenReturn(new HashMap<>());
        return context;
    }


    /*
     Conditions :
     - the animal exists 
     - the contraception exists
     - the method is compatible with its sex
     -if the methd is chirurgical, its compatible with its age
     - if the methd is hormonal, its compatible with its age
     - the method is compatible with the previous one
     */
    @Test
    public void shouldReturnTrueIfAllTheConditionsAreTrue() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));

        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
     @Test
    public void shouldReturnFalseIfTheAnimalDoesNotExist() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
             FindAnimal findAnimal = givenFindAnimal(null);
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseIfTheMethodDoesNotExist() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(null);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseIfThereIsIncompatibilityWithTheHormonalContraception() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(false);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseIfThereIsIncompatibilityWithTheChirurgicalContraception() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(false);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseIfThereIsIncompatibilityWithThePreviousMethod() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(false);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(true);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseIfThereIsIncompatibilityWithTheSex() {
        // Given
        FindingContraceptionFunction findingContraception = givenFindingContraception(ContraceptionMethod.NONE);
        FindAnimal findAnimal = givenFindAnimal(Mockito.mock(Animal.class));
        CanHaveAHormonalContraceptionPredicate hormonalCompatibility = givenHormonal(true);
        CanHaveAChirurgicalContraceptionPredicate chirurgicalContraceptionPredicate = givenChirurgical(true);
        IsContraceptionCompatibleWithPreviousPredicate compatibleWithPreviousPredicate = givenPrevious(true);
        IsContraceptionCompatibleWithSexPredicate compatibleWithSexPredicate = givenSex(false);
        AnimalUpdateContraceptionValidator validator = new AnimalUpdateContraceptionValidator(
                findingContraception,
                findAnimal,
                hormonalCompatibility,
                chirurgicalContraceptionPredicate,
                compatibleWithPreviousPredicate,
                compatibleWithSexPredicate);
        AnimalUpdateContraceptionContext context = givenContext();
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
