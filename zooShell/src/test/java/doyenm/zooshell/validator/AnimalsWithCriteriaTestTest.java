package doyenm.zooshell.validator;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.validator.criteria.AnimalsListWithDietCriteriaValidator;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalsWithCriteriaTestTest {
    
    AnimalsListWithDietCriteriaValidator givenDietValidator(boolean expected){
        AnimalsListWithDietCriteriaValidator validator = Mockito.mock(AnimalsListWithDietCriteriaValidator.class);
        Mockito.when(validator.test(Mockito.any(LsWithCriteriaContext.class))).thenReturn(expected);
        return validator;
    }
    
    LsWithCriteriaContext givenContext(){
        return Mockito.mock(LsWithCriteriaContext.class);
    }

//    @Test
//    public void shouldReturnTrueWhenAllTheCriteriaReturnTrue(){
//        // Given
//        AnimalsListWithDietCriteriaValidator dietValidator = givenDietValidator(true);
//        LsWithCriteriaContext context = givenContext();
//        AnimalsWithCriteria animalsWithCriteria = new AnimalsWithCriteria(dietValidator, null, null, null);
//        // When
//        boolean result = animalsWithCriteria.test(context);
//        // Then
//        Assertions.assertThat(result).isTrue();
//    }
//    
//      @Test
//    public void shouldReturnFalseWhenTheDietCriteriaReturnsFalse(){
//        // Given
//        AnimalsListWithDietCriteriaValidator dietValidator = givenDietValidator(false);
//        LsWithCriteriaContext context = givenContext();
//        AnimalsWithCriteria animalsWithCriteria = new AnimalsWithCriteria(dietValidator, null, null, null);
//        // When
//        boolean result = animalsWithCriteria.test(context);
//        // Then
//        Assertions.assertThat(result).isFalse();
//    }
}
