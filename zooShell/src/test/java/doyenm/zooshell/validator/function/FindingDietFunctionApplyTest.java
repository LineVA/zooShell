package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Diet;
import doyenm.zooshell.validator.context.FindingDietContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingDietFunctionApplyTest {

    private FindingDietContext givenContextWithInput(String input) {
        FindingDietContext context = Mockito.mock(FindingDietContext.class);
        Mockito.when(context.getConvertedDiet()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedDiet(Mockito.any(Diet.class));
        Mockito.when(context.getDiet()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedDietWhenTheInputIsIntegerCorrespondingToAnExistingDiet() {
        // Given
        String input = "0";
        FindingDietContext context = givenContextWithInput(input);
        FindingDietFunction function = new FindingDietFunction();
        // When
        FindingDietContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedDiet()).isEqualTo(Diet.NONE);
    }

    @Test
    public void shouldSetTheConvertedDietWhenTheInputIsStringCorrespondingToAnExistingDiet() {
        // Given
        String input = "none";
        FindingDietContext context = givenContextWithInput(input);
        FindingDietFunction function = new FindingDietFunction();
        // When
        FindingDietContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedDiet()).isEqualTo(Diet.NONE);
    }

    @Test
    public void shouldSetTheConvertedDietToNullWhenTheInputIsStringNotCorrespondingToAnExistingDiet() {
        // Given
        String input = "FALSE_NONE";
        FindingDietContext context = givenContextWithInput(input);
        FindingDietFunction function = new FindingDietFunction();
        // When
        FindingDietContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedDiet()).isNull();
    }

    @Test
    public void shouldSetTheConvertedDietToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingDiet() {
        // Given
        String input = "111";
        FindingDietContext context = givenContextWithInput(input);
        FindingDietFunction function = new FindingDietFunction();
        // When
        FindingDietContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedDiet()).isNull();
    }
}
