package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PaddockExtensionContextConvertTest {

    private PaddockExtensionCreationContext subject;

    private int x;
    private int y;
    private int width;
    private int height;

    @Before
    public void setUp(){
        x = RandomUtils.nextInt();
        y = RandomUtils.nextInt();
        width = RandomUtils.nextInt();
        height = RandomUtils.nextInt();
        subject = new PaddockExtensionCreationContext(
                mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                String.valueOf(x),
                String.valueOf(y),
                String.valueOf(width),
                String.valueOf(height)
        );
    }

    @Test
    public void shouldReturnTheListOfTheCoordinatesOfThePaddockAndItsExtensions(){
        // Given
        // When
        subject.convert();
        // Then
        assertThat(subject).isNotNull();
        assertThat(subject.getConvertedX()).isEqualTo(x);
        assertThat(subject.getConvertedY()).isEqualTo(y);
        assertThat(subject.getConvertedWidth()).isEqualTo(width);
        assertThat(subject.getConvertedHeight()).isEqualTo(height);
    }

}
