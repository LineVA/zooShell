package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.testUtils.TestUtils;
import doyenm.zooshell.validator.context.FindingKeeperContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

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
        String input = TestUtils.generateString();
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
        FindingKeeperContext context = givenContextWithInput(TestUtils.generateString(), TestUtils.generateString());
        FindingKeeperFunction function = new FindingKeeperFunction();
        // When
        FindingKeeperContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getConvertedKeeper()).isNull();
    }
}