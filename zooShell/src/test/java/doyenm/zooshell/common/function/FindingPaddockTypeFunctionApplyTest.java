package doyenm.zooshell.common.function;

import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.common.context.FindingPaddockTypeContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingPaddockTypeFunctionApplyTest {

    private FindingPaddockTypeContext givenContextWithInput(String input) {
        FindingPaddockTypeContext context = Mockito.mock(FindingPaddockTypeContext.class);
        Mockito.when(context.getConvertedType()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedType(Mockito.any(PaddockType.class));
        Mockito.when(context.getType()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedTypeWhenTheInputIsIntegerCorrespondingToAnExistingType() {
        // Given
        String input = "1";
        FindingPaddockTypeContext context = givenContextWithInput(input);
        FindingPaddockTypeFunction function = new FindingPaddockTypeFunction();
        // When
        FindingPaddockTypeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedType()).isEqualTo(PaddockType.AQUARIUM);
    }
    
    
    @Test
    public void shouldSetTheConvertedTypeWhenTheInputIsStringCorrespondingToAnExistingType() {
        // Given
        String input = "AQUARIUM";
        FindingPaddockTypeContext context = givenContextWithInput(input);
        FindingPaddockTypeFunction function = new FindingPaddockTypeFunction();
        // When
        FindingPaddockTypeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedType()).isEqualTo(PaddockType.AQUARIUM);
    }
    
    
    @Test
    public void shouldSetTheConvertedTypeWhenTheInputIsIntegerNotCorrespondingToAnExistingType() {
        // Given
        String input = "111";
        FindingPaddockTypeContext context = givenContextWithInput(input);
        FindingPaddockTypeFunction function = new FindingPaddockTypeFunction();
        // When
        FindingPaddockTypeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedType()).isNull();
    }
    
    
    @Test
    public void shouldSetTheConvertedTypeWhenTheInputIsStringNotCorrespondingToAnExistingType() {
        // Given
        String input = "FALSE_AQUARUM";
        FindingPaddockTypeContext context = givenContextWithInput(input);
        FindingPaddockTypeFunction function = new FindingPaddockTypeFunction();
        // When
        FindingPaddockTypeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedType()).isNull();
    }

}
