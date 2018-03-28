package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.rename.PaddockChangeNameContext;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameContextGetPaddocksTest {

    private Zoo givenZoo(String pad1, String pad2) {
        Zoo zoo = mock(Zoo.class);
        Map<String, Paddock> map = new HashMap<>();
        map.put(pad1, mock(Paddock.class));
        map.put(pad2, mock(Paddock.class));
        when(zoo.getPaddocks()).thenReturn(map);
        return zoo;
    }

    @Test
    public void shouldReturnTheNamesOfThePaddocksOfTheZoo() {
        // Given
        String pad1 = RandomStringUtils.randomAlphanumeric(10);
        String pad2 = RandomStringUtils.randomAlphanumeric(10);
        PaddockChangeNameContext subject = new PaddockChangeNameContext(givenZoo(pad1, pad2),
                RandomStringUtils.random(10), RandomStringUtils.randomAlphabetic(10));
        // When
        Set<String> actualPaddocksName = subject.getPaddocks();
        // Then
        Assertions.assertThat(actualPaddocksName).hasSize(2);
        Assertions.assertThat(actualPaddocksName).contains(pad1, pad2);
    }

}
