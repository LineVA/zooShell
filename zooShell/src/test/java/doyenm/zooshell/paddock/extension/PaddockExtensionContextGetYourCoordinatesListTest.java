package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaddockExtensionContextGetYourCoordinatesListTest {

    private PaddockExtensionCreationContext subject;

    private Paddock paddock;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
        subject = new PaddockExtensionCreationContext(
                mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5)
        );

        subject.setConvertedPaddock(paddock);
    }

    @Test
    public void shouldReturnTheListOfTheCoordinatesOfThePaddockAndItsExtensions(){
        // Given
        Coordinates coordinates = genCoordinates();
        Coordinates extensionCoordinates = genCoordinates();

        when(paddock.getCoordinates()).thenReturn(coordinates);
        when(paddock.getExtensions()).thenReturn(Arrays.asList(extensionCoordinates));
        // When
        List<Coordinates> result = subject.getYourCoordinatesList();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(coordinates, extensionCoordinates);
    }

    private Coordinates genCoordinates(){
        Position position = mock(Position.class);
        when(position.getX()).thenReturn(RandomUtils.nextInt());
        when(position.getY()).thenReturn(RandomUtils.nextInt());
        Coordinates coordinates = mock(Coordinates.class);
        when(coordinates.getWidth()).thenReturn(RandomUtils.nextInt());
        when(coordinates.getHeight()).thenReturn(RandomUtils.nextInt());
        when(coordinates.getPosition()).thenReturn(position);
        return coordinates;
    }
}
