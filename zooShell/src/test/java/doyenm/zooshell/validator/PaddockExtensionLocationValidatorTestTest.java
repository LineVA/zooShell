package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionLocationValidatorTestTest {

    private Coordinates givenCoordinatesWithValues(int x, int y, int width, int height) {
        Coordinates coor = Mockito.mock(Coordinates.class);
        Mockito.when(coor.getHeight()).thenReturn(height);
        Mockito.when(coor.getWidth()).thenReturn(width);
        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getX()).thenReturn(x);
        Mockito.when(position.getY()).thenReturn(y);
        Mockito.when(coor.getPosition()).thenReturn(position);
        return coor;
    }

    private Paddock givenPaddockWithCoordinates(Coordinates coor) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getCoordinates()).thenReturn(coor);
        return pad;
    }

    private PaddockExtensionCreationContext givenContextWithConvertedCoordinatesAndPaddock(
            Coordinates coordinates, Paddock currentPaddock, Paddock otherPaddock) {
        PaddockExtensionCreationContext context = Mockito.mock(PaddockExtensionCreationContext.class);
        Coordinates coor2 = new Coordinates(coordinates.getPosition(), coordinates.getWidth(), coordinates.getHeight());
        int x = coor2.getPosition().getX();
        int y = coor2.getPosition().getY();
        Mockito.when(context.getConvertedHeight()).thenReturn(coor2.getHeight());
        Mockito.when(context.getConvertedWidth()).thenReturn(coor2.getWidth());
        Mockito.when(context.getConvertedX()).thenReturn(x);
        Mockito.when(context.getConvertedY()).thenReturn(y);
        Mockito.when(context.getConvertedPaddock()).thenReturn(currentPaddock);
        Coordinates coor1 = otherPaddock.getCoordinates();
        Coordinates coor3 = currentPaddock.getCoordinates();
        Mockito.when(context.getCoordinatesListExceptYours()).thenReturn(Arrays.asList(coor1));
        Mockito.when(context.getYourCoordinatesList()).thenReturn(Arrays.asList(coor3));
        return context;
    }

    @Test
    public void shouldReturnFalseWhenItOverlapsWithOthers() {
        // Given
        PaddockExtensionLocationValidator validator = new PaddockExtensionLocationValidator();
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        Coordinates coordinates = givenCoordinatesWithValues(x, y, width, height);
        Coordinates padCoor = givenCoordinatesWithValues(10, 10, 10, 10);
        Coordinates otherPadCoor = givenCoordinatesWithValues(5, 5, 2, 2);
        Paddock paddock = givenPaddockWithCoordinates(padCoor);
        Paddock otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        PaddockExtensionCreationContext context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenItNotOverlapsWithItself() {
        // Given
        PaddockExtensionLocationValidator validator = new PaddockExtensionLocationValidator();
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        Coordinates coordinates = givenCoordinatesWithValues(x, y, width, height);
        Coordinates padCoor = givenCoordinatesWithValues(15, 15, 10, 10);
        Coordinates otherPadCoor = givenCoordinatesWithValues(1, 1, 1, 1);
        Paddock paddock = givenPaddockWithCoordinates(padCoor);
        Paddock otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        PaddockExtensionCreationContext context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnFalseWhenItNotOverlapsWithItselfAndItOverlapsWithOthers() {
        // Given
        PaddockExtensionLocationValidator validator = new PaddockExtensionLocationValidator();
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        Coordinates coordinates = givenCoordinatesWithValues(x, y, width, height);
        Coordinates padCoor = givenCoordinatesWithValues(15, 15, 10, 10);
        Coordinates otherPadCoor = givenCoordinatesWithValues(1, 1, 5, 5);
        Paddock paddock = givenPaddockWithCoordinates(padCoor);
        Paddock otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        PaddockExtensionCreationContext context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
     @Test
    public void shouldReturnTrueWhenItOverlapsWithItselfAndItNotOverlapsWithOthers() {
        // Given
        PaddockExtensionLocationValidator validator = new PaddockExtensionLocationValidator();
        int x = 5;
        int y = 5;
        int width = 5;
        int height = 5;
        Coordinates coordinates = givenCoordinatesWithValues(x, y, width, height);
        Coordinates padCoor = givenCoordinatesWithValues(10, 10, 10, 10);
        Coordinates otherPadCoor = givenCoordinatesWithValues(1, 1, 1, 1);
        Paddock paddock = givenPaddockWithCoordinates(padCoor);
        Paddock otherPaddock = givenPaddockWithCoordinates(otherPadCoor);
        PaddockExtensionCreationContext context = givenContextWithConvertedCoordinatesAndPaddock(
                coordinates, paddock, otherPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }


}
