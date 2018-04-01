package doyenm.zooshell.common.function;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.common.context.FindingPaddockContext;
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
public class FindingPaddockByNameFunctionApplyTest {

    private FindingPaddockContext givenContextWithInput(String input, String key) {
        FindingPaddockContext context = Mockito.mock(FindingPaddockContext.class);
        Map<String, Paddock> paddocks = new HashMap<>();
        Paddock pad = Paddock.builder().name(key).build();
        paddocks.put(key.toUpperCase(), pad);
        Mockito.when(context.getPaddock()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(context).setPaddock(Mockito.any(Paddock.class));
        Mockito.when(context.getPaddockName()).thenReturn(input);
        Mockito.when(context.getPaddocks()).thenReturn(paddocks);
        return context;
    }

    @Test
    public void shouldSetTheConvertedPaddockWhenTheInputIsCorrespondingToAnExistingPaddock() {
        // Given
        String input = RandomStringUtils.randomAlphabetic(10);
        FindingPaddockContext context = givenContextWithInput(input, input);
        FindingPaddockByNameFunction function = new FindingPaddockByNameFunction();
        // When
        FindingPaddockContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getPaddock().getName()).isEqualTo(input);
    }

    @Test
    public void shouldSetTheConvertedPaddockToNullWhenTheInputIsNotCorrespondingToAnExistingPaddock() {
        // Given
        FindingPaddockContext context = givenContextWithInput(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
        FindingPaddockByNameFunction function = new FindingPaddockByNameFunction();
        // When
        FindingPaddockContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getPaddock()).isNull();
    }
}
