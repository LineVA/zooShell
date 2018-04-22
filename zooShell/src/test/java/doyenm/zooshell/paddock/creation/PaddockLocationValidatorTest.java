package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.model.Paddock;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockLocationValidatorTest {

    private PaddockLocationValidator subject;


    @Before
    public void setUp(){
        subject = new PaddockLocationValidator();
    }


    @Test
    public void shouldReturnTrueWhenThereIsNoOverlap() {
        // Given
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreationContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 25, 25, 20, 10);
        // When
        PaddockCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
    
      @Test
    public void shouldReturnTrueWhenThereIsVerticalOverlap() {
        // Given
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreationContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 10, 25, 10, 10);
        // When
          PaddockCreationContext result = subject.apply(context);
          // Then
          assertThat(result).isNotNull();
          assertThat(result.getErrors()).isNotNull();
          assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void shouldReturnTrueWhenThereIsHorizontalOverlap() {
        // Given
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreationContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 25, 10, 10, 10);
        // When
        PaddockCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void shouldReturnFalseWhenThereIsBothHorizontalAndVerticalOverlap() {
        // Given
        Paddock paddock = givenPaddockWithCoordinates(10, 10, 10, 10);
        PaddockCreationContext context = givenPaddockCreationContextWithPaddocksListAndCoordinates(Arrays.asList(paddock), 15, 15, 10, 10);
        // When
        PaddockCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    private Paddock givenPaddockWithCoordinates(int x, int y, int width, int height) {
        Paddock pad = mock(Paddock.class);
        when(pad.getX()).thenReturn(x);
        when(pad.getY()).thenReturn(y);
        when(pad.getWidth()).thenReturn(width);
        when(pad.getHeight()).thenReturn(height);
        return pad;
    }

    private PaddockCreationContext givenPaddockCreationContextWithPaddocksListAndCoordinates(
            List<Paddock> paddocks, int x, int y, int width, int height) {
        PaddockCreationContext context = mock(PaddockCreationContext.class);
        when(context.getPaddocksList()).thenReturn(paddocks);
        when(context.getConvertedX()).thenReturn(x);
        when(context.getConvertedY()).thenReturn(y);
        when(context.getConvertedWidth()).thenReturn(width);
        when(context.getConvertedHeight()).thenReturn(height);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        return context;
    }

}
