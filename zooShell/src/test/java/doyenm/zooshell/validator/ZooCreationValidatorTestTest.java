package doyenm.zooshell.validator;

import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

/**
 *
 * @author doyenm
 */
public class ZooCreationValidatorTestTest {
    
     private IntegerValuePredicates givenIntegerValuePredicates(boolean bool1, boolean bool2, 
             boolean bool3, boolean bool4, boolean bool5, boolean bool6) {
        IntegerValuePredicates mock = Mockito.mock(IntegerValuePredicates.class);
        Mockito.when(mock.mustBeGreaterOrEqualsThan(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(bool1).thenReturn(bool2).thenReturn(bool3).thenReturn(bool5);
          Mockito.when(mock.mustBeLowerOrEqualsThan(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(bool4).thenReturn(bool6);
        return mock;
    } 
     
     private NameValidator givenNameValidator(boolean bool) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class)))
                .thenReturn(bool);
        return mock;
    } 

    private ZooCreationContext givenContext() {
        ZooCreationContext context = Mockito.mock(ZooCreationContext.class);
        Mockito.when(context.getName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getConvertedHeight()).thenReturn(RandomUtils.nextInt());
        Mockito.when(context.getConvertedWidth()).thenReturn(RandomUtils.nextInt());
        Mockito.when(context.getConvertedSpeed()).thenReturn(RandomUtils.nextInt());
        Mockito.when(context.getConvertedHorizon()).thenReturn(RandomUtils.nextInt());
        Mockito.doNothing().when(context).convert();
        return context;
    }
    
    @Test
    public void shouldReturnTrueWhenEverythingIsOk(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, true, true, true, true);
        NameValidator nameValidator = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, nameValidator, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
  
    @Test
    public void shouldReturnFalseWhenTheWidthIsTooLow(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(false, true, true, true, true, true);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheHeightIsTooLow(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, false, true, true, true, true);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheHorizonIsTooLowInAbsoluteValue(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, false, true, true, true);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheHorizonIsTooLowForThisZoo(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, true, false, true, true);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
      @Test
    public void shouldReturnFalseWhenTheSpeedIsTooLow(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, true, true, false, true);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheSpeedIsTooHight(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, true, true, true, false);
        NameValidator stringLengthPredicates = givenNameValidator(true);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenTheNameIsTooLong(){
        // Given
        IntegerValuePredicates integerValuePredicates = givenIntegerValuePredicates(true, true, true, true, true, true);
        NameValidator stringLengthPredicates = givenNameValidator(false);
        ZooCreationContext context = givenContext();
        ZooCreationValidator validator = new ZooCreationValidator(
                integerValuePredicates, stringLengthPredicates, 
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt(), RandomUtils.nextInt(),
                RandomUtils.nextInt());
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
