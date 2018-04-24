package doyenm.zooshell.paddock.creation;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PaddockCreationContextConvertTest {

    private PaddockCreationContext subject;

    @Test
    public void shouldConvertCoordinatesOfTheEntry(){
        // Given
        int x = RandomUtils.nextInt();
        int y = RandomUtils.nextInt();
        int width = RandomUtils.nextInt();
        int height = RandomUtils.nextInt();
        subject = new PaddockCreationContext(null, null,
                String.valueOf(x), String.valueOf(y),
                String.valueOf(width), String.valueOf(height));
        // When
        subject.convert();
        // Then
        assertThat(subject.getConvertedX()).isEqualTo(x);
        assertThat(subject.getConvertedY()).isEqualTo(y);
        assertThat(subject.getConvertedWidth()).isEqualTo(width);
        assertThat(subject.getConvertedHeight()).isEqualTo(height);
    }
}
