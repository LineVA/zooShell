package doyenm.zooshell.common.function;

import doyenm.zooshell.common.context.FindingBiomeContext;
import doyenm.zooshell.model.Biome;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class FindingBiomeFunctionApplyTest {

    private FindingBiomeFunction subject;
    private FindingBiomeContext context;

    @Before
    public void setUp() {
        context = mock(FindingBiomeContext.class);
        when(context.getConvertedBiome()).thenCallRealMethod();
        doCallRealMethod().when(context).setConvertedBiome(any(Biome.class));

        subject = new FindingBiomeFunction();
    }

    @Test
    public void shouldSetTheConvertedBiomeWhenTheInputIsIntegerCorrespondingToAnExistingBiome() {
        // Given
        String input = "11";
        when(context.getBiome()).thenReturn(input);
        // When
        Biome result = subject.apply(context);
        // Then 
        assertThat(result).isEqualTo(Biome.TUNDRA);
    }

    @Test
    public void shouldSetTheConvertedBiomeWhenTheInputIsStringCorrespondingToAnExistingBiomeWithoutSpace() {
        // Given
        String input = "tundra";
        when(context.getBiome()).thenReturn(input);
        // When
        Biome result = subject.apply(context);
        // Then
        assertThat(result).isEqualTo(Biome.TUNDRA);
    }

    @Test
    public void shouldSetTheConvertedBiomeWhenTheInputIsStringCorrespondingToAnExistingBiomeWithSpace() {
        // Given
        String input = "tropical grasslands";
        when(context.getBiome()).thenReturn(input);
        // When
        Biome result = subject.apply(context);
        // Then
        assertThat(result).isEqualTo(Biome.TROPICAL_GRASSLANDS);
    }

    @Test
    public void shouldSetTheConvertedBiomeToNullWhenTheInputIsStringNotCorrespondingToAnExistingBiome() {
        // Given
        String input = "FALSE_TUNDRA";
        when(context.getBiome()).thenReturn(input);
        // When
        Biome result = subject.apply(context);
        // Then
        assertThat(result).isNull();
    }

    @Test
    public void shouldSetTheConvertedBiomeToNullWhenTheInputIsIntegerNotCorrespondingToAnExistingBiome() {
        // Given
        String input = "111";
        when(context.getBiome()).thenReturn(input);
        // When
        Biome result = subject.apply(context);
        // Then
        assertThat(result).isNull();
    }

}
