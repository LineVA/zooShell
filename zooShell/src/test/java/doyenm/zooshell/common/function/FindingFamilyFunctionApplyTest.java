package doyenm.zooshell.common.function;

import doyenm.zooshell.model.Family;
import doyenm.zooshell.common.context.FindingFamilyContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingFamilyFunctionApplyTest {

    private FindingFamilyContext givenContextWithInput(String input) {
        FindingFamilyContext context = Mockito.mock(FindingFamilyContext.class);
        Mockito.when(context.getConvertedFamily()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedFamily(Mockito.any(Family.class));
        Mockito.when(context.getFamily()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedFamilyWhenTheInputIsIntegerCorrespondingToAnExistingFamily() {
        // Given
        String input = "1";
        FindingFamilyContext context = givenContextWithInput(input);
        FindingFamilyFunction function = new FindingFamilyFunction();
        // When
        FindingFamilyContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedFamily()).isEqualTo(Family.LEMURIDAE);
    }
    
     @Test
    public void shouldSetTheConvertedFamilyWhenTheInputIsStringCorrespondingToAnExistingFamilyWithoutSpace() {
        // Given
        String input = "lemuridae";
        FindingFamilyContext context = givenContextWithInput(input);
        FindingFamilyFunction function = new FindingFamilyFunction();
        // When
        FindingFamilyContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedFamily()).isEqualTo(Family.LEMURIDAE);
    }
    
//    @Test
//    public void shouldSetTheConvertedFamilyWhenTheInputIsStringCorrespondingToAnExistingFamilyeWithSpace() {
//        // Given
//        String input = "medical training";
//        FindingFamilyContext context = givenContextWithInput(input);
//        FindingFamilyFunction function = new FindingFamilyFunction();
//        // When
//        FindingFamilyContext actualContext = function.apply(context);
//        // Then 
//        Assertions.assertThat(actualContext.getConvertedFamily()).isEqualTo(TaskType.MEDICAL_TRAINING);
//    }
//    
    @Test
    public void shouldSetTheConvertedFamilyToNullWhenTheInputIsStringNotCorrespondingToAnExistingFamily() {
        // Given
        String input = "FALSE_LEMURIDAE";
        FindingFamilyContext context = givenContextWithInput(input);
        FindingFamilyFunction function = new FindingFamilyFunction();
        // When
        FindingFamilyContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedFamily()).isNull();
    }
    
     @Test
    public void shouldSetTheConvertedFamilyToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingFamily() {
        // Given
        String input = "111";
        FindingFamilyContext context = givenContextWithInput(input);
        FindingFamilyFunction function = new FindingFamilyFunction();
        // When
        FindingFamilyContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedFamily()).isNull();
    }

}
