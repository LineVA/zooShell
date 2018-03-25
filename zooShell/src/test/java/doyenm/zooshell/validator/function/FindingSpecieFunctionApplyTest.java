package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Names;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.validator.context.FindingSpecieContext;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class FindingSpecieFunctionApplyTest {

    private FindingSpecieContext givenContextWithInput(String input, String key) {
        FindingSpecieContext context = Mockito.mock(FindingSpecieContext.class);
        Map<String, Specie> species = new HashMap<>();
        Specie spec = new Specie();
        Names names = new Names();
        names.setName(key);
        spec.setNames(names);
        species.put(key.toUpperCase(), spec);
        Mockito.when(context.getSpecie()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setSpecie(Mockito.any(Specie.class));
        Mockito.when(context.getSpecieName()).thenReturn(input);
        Mockito.when(context.getSpecies()).thenReturn(species);
        return context;
    }

    @Test
    public void shouldSetTheConvertedSpeceWhenTheInputIsCorrespondingToAnExistingSpecie() {
        // Given
        String input = RandomStringUtils.randomAlphabetic(10);
        FindingSpecieContext context = givenContextWithInput(input, input);
        FindingSpecieFunction function = new FindingSpecieFunction();
        // When
        FindingSpecieContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getSpecie().getNames().getName()).isEqualTo(input);
    }

    @Test
    public void shouldSetTheConvertedSpecieToNullWhenTheInputIsNotCorrespondingToAnExistingSpecie() {
        // Given
        FindingSpecieContext context = givenContextWithInput(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        FindingSpecieFunction function = new FindingSpecieFunction();
        // When
        FindingSpecieContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getSpecie()).isNull();
    }
}
