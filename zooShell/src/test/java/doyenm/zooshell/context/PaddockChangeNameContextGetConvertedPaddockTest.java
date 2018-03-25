package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameContextGetConvertedPaddockTest {

    @Test
    public void shouldReturnTheConvertedPaddockOfTheContext() {
        // Given
        PaddockChangeNameContext subject = new PaddockChangeNameContext(mock(Zoo.class), 
                RandomStringUtils.random(10), RandomStringUtils.randomAlphabetic(10));
        Paddock pad = mock(Paddock.class);
        // When
        subject.setConvertedPaddock(pad);
        Paddock actualPaddock = subject.getConvertedPaddock();
        // Then
        Assertions.assertThat(actualPaddock).isEqualTo(pad);
    }

}
