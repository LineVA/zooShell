package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class PaddockExtensionLocationValidatorTestTest {

    private PaddockExtensionLocationValidator subject;

    private PaddockExtensionCreationContext context;
    private Coordinates coordinates;
    private Coordinates padCoor;
    private Coordinates otherPadCoor;
    private Paddock paddock;
    private Paddock otherPaddock;

    @Before
    public void setUp() {
        context = mock(PaddockExtensionCreationContext.class);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        coordinates = mock(Coordinates.class);
        padCoor = mock(Coordinates.class);
        otherPadCoor = mock(Coordinates.class);
        paddock = mock(Paddock.class);
        otherPaddock = mock(Paddock.class);
        subject = new PaddockExtensionLocationValidator();
    }

    @Test
    public void shouldReturnFalseWhenItOverlapsWithOthers() {
        // Given
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        coordinates = givenCoordinatesWithValues(x, y, width, height);
        padCoor = givenCoordinatesWithValues(10, 10, 10, 10);
        otherPadCoor = givenCoordinatesWithValues(5, 5, 2, 2);
        paddock = givenPaddockWithCoordinates(padCoor);
        otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseWhenItNotOverlapsWithItself() {
        // Given
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        coordinates = givenCoordinatesWithValues(x, y, width, height);
        padCoor = givenCoordinatesWithValues(15, 15, 10, 10);
        otherPadCoor = givenCoordinatesWithValues(1, 1, 1, 1);
        paddock = givenPaddockWithCoordinates(padCoor);
        otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseWhenItNotOverlapsWithItselfAndItOverlapsWithOthers() {
        // Given
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        coordinates = givenCoordinatesWithValues(x, y, width, height);
        padCoor = givenCoordinatesWithValues(15, 15, 10, 10);
        otherPadCoor = givenCoordinatesWithValues(1, 1, 5, 5);
        paddock = givenPaddockWithCoordinates(padCoor);
        otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnTrueWhenItOverlapsWithItselfAndItNotOverlapsWithOthers() {
        // Given
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        coordinates = givenCoordinatesWithValues(x, y, width, height);
        padCoor = givenCoordinatesWithValues(10, 10, 10, 10);
        otherPadCoor = givenCoordinatesWithValues(1, 1, 1, 1);
        paddock = givenPaddockWithCoordinates(padCoor);
        otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    private Coordinates givenCoordinatesWithValues(int x, int y, int width, int height) {
        Coordinates coor = mock(Coordinates.class);
        when(coor.getHeight()).thenReturn(height);
        when(coor.getWidth()).thenReturn(width);
        Position position = mock(Position.class);
        when(position.getX()).thenReturn(x);
        when(position.getY()).thenReturn(y);
        when(coor.getPosition()).thenReturn(position);
        return coor;
    }

    private Paddock givenPaddockWithCoordinates(Coordinates coor) {
        Paddock pad = mock(Paddock.class);
        when(pad.getCoordinates()).thenReturn(coor);
        return pad;
    }


    private PaddockExtensionCreationContext givenContextWithConvertedCoordinatesAndPaddock(
            Coordinates coordinates, Paddock currentPaddock, Paddock otherPaddock) {
        Coordinates coor2 = new Coordinates(coordinates.getPosition(), coordinates.getWidth(), coordinates.getHeight());
        int x = coor2.getPosition().getX();
        int y = coor2.getPosition().getY();
        when(context.getConvertedHeight()).thenReturn(coor2.getHeight());
        when(context.getConvertedWidth()).thenReturn(coor2.getWidth());
        when(context.getConvertedX()).thenReturn(x);
        when(context.getConvertedY()).thenReturn(y);
        when(context.getConvertedPaddock()).thenReturn(currentPaddock);
        Coordinates coor1 = otherPaddock.getCoordinates();
        Coordinates coor3 = currentPaddock.getCoordinates();
        when(context.getCoordinatesListExceptYours()).thenReturn(Arrays.asList(coor1));
        when(context.getYourCoordinatesList()).thenReturn(Arrays.asList(coor3));
        return context;
    }


}
