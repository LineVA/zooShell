package doyenm.zooshell.common.function;

import doyenm.zooshell.animal.contraception.FindingContraceptionFunction;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.animal.contraception.FindingContraceptionContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingContraceptionFunctionApplyTest {

    private FindingContraceptionContext givenContextWithInput(String input) {
        FindingContraceptionContext context = Mockito.mock(FindingContraceptionContext.class);
        Mockito.when(context.getConvertedContraception()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedContraception(Mockito.any(ContraceptionMethod.class));
        Mockito.when(context.getContraception()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedContraceptionWhenTheInputIsIntegerCorrespondingToAnExistingContraception() {
        // Given
        String input = "0";
        FindingContraceptionContext context = givenContextWithInput(input);
        FindingContraceptionFunction function = new FindingContraceptionFunction();
        // When
        FindingContraceptionContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedContraception()).isEqualTo(ContraceptionMethod.NONE);
    }

    @Test
    public void shouldSetTheConvertedContraceptionWhenTheInputIsStringCorrespondingToAnExistingContraceptionWithoutSpace() {
        // Given
        String input = "none";
        FindingContraceptionContext context = givenContextWithInput(input);
        FindingContraceptionFunction function = new FindingContraceptionFunction();
        // When
        FindingContraceptionContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedContraception()).isEqualTo(ContraceptionMethod.NONE);
    }
    
      @Test
    public void shouldSetTheConvertedContraceptionWhenTheInputIsStringCorrespondingToAnExistingContraceptionWithSpace() {
        // Given
        String input = "female implant";
        FindingContraceptionContext context = givenContextWithInput(input);
        FindingContraceptionFunction function = new FindingContraceptionFunction();
        // When
        FindingContraceptionContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedContraception()).isEqualTo(ContraceptionMethod.FEMALE_IMPLANT);
    }

    @Test
    public void shouldSetTheConvertedContraceptionToNullWhenTheInputIsStringNotCorrespondingToAnExistingContraception() {
        // Given
        String input = "FALSE_NONE";
        FindingContraceptionContext context = givenContextWithInput(input);
        FindingContraceptionFunction function = new FindingContraceptionFunction();
        // When
        FindingContraceptionContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedContraception()).isNull();
    }

    @Test
    public void shouldSetTheConvertedContraceptionToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingContraception() {
        // Given
        String input = "111";
        FindingContraceptionContext context = givenContextWithInput(input);
        FindingContraceptionFunction function = new FindingContraceptionFunction();
        // When
        FindingContraceptionContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedContraception()).isNull();
    }
}
