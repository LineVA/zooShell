package doyenm.zooshell.paddock.entry;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PaddockEntryCreationContextConvertTest {

    private PaddockEntryCreationContext subject;

    @Test
    public void shouldConvertCoordinatesOfTheEntry(){
        // Given
        int x = RandomUtils.nextInt();
        int y = RandomUtils.nextInt();
        subject = new PaddockEntryCreationContext(null, null, String.valueOf(x), String.valueOf(y));
        // When
        subject.convert();
        // Then
        assertThat(subject.getConvertedX()).isEqualTo(x);
        assertThat(subject.getConvertedY()).isEqualTo(y);
    }
}
