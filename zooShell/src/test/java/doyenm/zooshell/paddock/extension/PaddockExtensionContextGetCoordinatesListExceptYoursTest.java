package doyenm.zooshell.paddock.extension;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaddockExtensionContextGetCoordinatesListExceptYoursTest {

    private PaddockExtensionCreationContext subject;

    private Paddock paddock;
    private Paddock otherPaddock;

    @Before
    public void setUp() {
        paddock = mock(Paddock.class);
        otherPaddock = mock(Paddock.class);
        Zoo zoo = mock(Zoo.class);
        Map<String, Paddock> paddocks = new HashMap<>();
        paddocks.put(RandomStringUtils.randomAlphabetic(5), paddock);
        paddocks.put(RandomStringUtils.randomAlphabetic(5), otherPaddock);
        when(zoo.getPaddocks()).thenReturn(paddocks);
        subject = new PaddockExtensionCreationContext(
                zoo,
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5)
        );

        subject.setConvertedPaddock(paddock);
    }

    @Test
    public void shouldReturnTheListOfTheCoordinatesOfThePaddockAndItsExtensions() {
        // Given
        Coordinates coordinates = genCoordinates();
        Coordinates extensionCoordinates = genCoordinates();
        when(paddock.getCoordinates()).thenReturn(coordinates);
        when(paddock.getExtensions()).thenReturn(Arrays.asList(extensionCoordinates));

        Coordinates otherCoordinates = genCoordinates();
        Coordinates otherExtensionCoordinates = genCoordinates();
        when(otherPaddock.getCoordinates()).thenReturn(otherCoordinates);
        when(otherPaddock.getExtensions()).thenReturn(Arrays.asList(otherExtensionCoordinates));

        // When
        List<Coordinates> result = subject.getCoordinatesListExceptYours();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(otherCoordinates, otherExtensionCoordinates);
    }

    private Coordinates genCoordinates() {
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
