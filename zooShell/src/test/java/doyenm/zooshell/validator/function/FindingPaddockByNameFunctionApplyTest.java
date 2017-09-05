package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.testUtils.TestUtils;
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
        String input = TestUtils.generateString();
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
        FindingPaddockContext context = givenContextWithInput(TestUtils.generateString(), TestUtils.generateString());
        FindingPaddockByNameFunction function = new FindingPaddockByNameFunction();
        // When
        FindingPaddockContext actualContext = function.apply(context);
        // Then 
        Assertions.assertThat(actualContext.getPaddock()).isNull();
    }
}
