package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.paddock.creation.PaddockCreationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.paddock.creation.PaddockLocationValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class PaddockLocationValidatorTest {

    private Paddock givenPaddockWithCoordinates(int x, int y, int width, int height) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getX()).thenReturn(x);
        Mockito.when(pad.getY()).thenReturn(y);
        Mockito.when(pad.getWidth()).thenReturn(width);
        Mockito.when(pad.getHeight()).thenReturn(height);
        return pad;
    }

    private PaddockCreationContext givenPaddockCreatioNContextWithPaddocksListAndCoordinates(
            List<Paddock> paddocks, int x, int y, int width, int height) {
        PaddockCreationContext context = Mockito.mock(PaddockCreationContext.class);
        Mockito.when(context.getPaddocksList()).thenReturn(paddocks);
        Mockito.when(context.getConvertedX()).thenReturn(x);
        Mockito.when(context.getConvertedY()).thenReturn(y);
        Mockito.when(context.getConvertedWidth()).thenReturn(width);
        Mockito.when(context.getConvertedHeight()).thenReturn(height);
        return context;
    }

    @Test
    public void shouldReturnTrueWhenThereIsNoOverlap() {
        // Given
        PaddockLocationValidator validator = new PaddockLocationValidator();
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreatioNContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 25, 25, 20, 10);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
      @Test
    public void shouldReturnTrueWhenThereIsVerticalOverlap() {
        // Given
        PaddockLocationValidator validator = new PaddockLocationValidator();
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreatioNContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 10, 25, 10, 10);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnTrueWhenThereIsHorizontalOverlap() {
        // Given
        PaddockLocationValidator validator = new PaddockLocationValidator();
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreatioNContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 25, 10, 10, 10);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsBothHorizontalAndVerticalOverlap() {
        // Given
        PaddockLocationValidator validator = new PaddockLocationValidator();
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreatioNContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 15, 15, 10, 10);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
