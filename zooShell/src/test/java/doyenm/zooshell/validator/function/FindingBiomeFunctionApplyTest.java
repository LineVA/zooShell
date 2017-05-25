package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.validator.context.FindingBiomeContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class FindingBiomeFunctionApplyTest {

    private FindingBiomeContext givenContextWithInput(String input) {
        FindingBiomeContext context = Mockito.mock(FindingBiomeContext.class);
        Mockito.when(context.getConvertedBiome()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedBiome(Mockito.any(Biome.class));
        Mockito.when(context.getBiome()).thenReturn(input);
        return context;
    }

    @Test
    public void shouldSetTheConvertedBiomeWhenTheInputIsIntegerCorrespondingToAnExistingBiome() {
        // Given
        String input = "11";
        FindingBiomeContext context = givenContextWithInput(input);
        FindingBiomeFunction function = new FindingBiomeFunction();
        // When
        FindingBiomeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedBiome()).isEqualTo(Biome.TUNDRA);
    }
    
     @Test
    public void shouldSetTheConvertedBiomeWhenTheInputIsStringCorrespondingToAnExistingBiome() {
        // Given
        String input = "tundra";
        FindingBiomeContext context = givenContextWithInput(input);
        FindingBiomeFunction function = new FindingBiomeFunction();
        // When
        FindingBiomeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedBiome()).isEqualTo(Biome.TUNDRA);
    }
    
    @Test
    public void shouldSetTheConvertedBiomeToNullWhenTheInputIsStringNotCorrespondingToAnExistingBiome() {
        // Given
        String input = "FALSE_TUNDRA";
        FindingBiomeContext context = givenContextWithInput(input);
        FindingBiomeFunction function = new FindingBiomeFunction();
        // When
        FindingBiomeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedBiome()).isNull();
    }
    
     @Test
    public void shouldSetTheConvertedBiomeToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingBiome() {
        // Given
        String input = "111";
        FindingBiomeContext context = givenContextWithInput(input);
        FindingBiomeFunction function = new FindingBiomeFunction();
        // When
        FindingBiomeContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedBiome()).isNull();
    }

}
