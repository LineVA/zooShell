package doyenm.zooshell.common.function;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.common.context.FindingKeeperContext;
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
public class FindingKeeperFunctionApplyTest {

    private FindingKeeperContext givenContextWithInput(String input, String key) {
        FindingKeeperContext context = Mockito.mock(FindingKeeperContext.class);
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        AnimalKeeper keeper = AnimalKeeper.builder().name(key).build();
        keepers.put(key, keeper);
        Mockito.when(context.getConvertedKeeper()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setConvertedKeeper(Mockito.any(AnimalKeeper.class));
        Mockito.when(context.getKeeper()).thenReturn(input);
        Mockito.when(context.getKeepers()).thenReturn(keepers);
        return context;
    }

    @Test
    public void shouldSetTheConvertedPaddockWhenTheInputIsCorrespondingToAnExistingPaddock() {
        // Given
        String input = RandomStringUtils.randomAlphabetic(10);
        FindingKeeperContext context = givenContextWithInput(input, input);
        FindingKeeperFunction function = new FindingKeeperFunction();
        // When
        FindingKeeperContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedKeeper().getName()).isEqualTo(input);
    }

    @Test
    public void shouldSetTheConvertedPaddockToNullWhenTheInputIsNotCorrespondingToAnExistingPaddock() {
        // Given
        FindingKeeperContext context = givenContextWithInput(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        FindingKeeperFunction function = new FindingKeeperFunction();
        // When
        FindingKeeperContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedKeeper()).isNull();
    }
}
