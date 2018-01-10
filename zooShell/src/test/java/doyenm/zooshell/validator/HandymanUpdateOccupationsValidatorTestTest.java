package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.context.HandymanUpdateOccupationsContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class HandymanUpdateOccupationsValidatorTestTest {

    private HandymanUpdateOccupationsContext givenContext(Handyman handyman, Paddock pad) {
        HandymanUpdateOccupationsContext context = Mockito.mock(HandymanUpdateOccupationsContext.class);
        when(context.getHandymanName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getPaddockName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getHandyman()).thenReturn(handyman);
        when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        when(context.getPaddock()).thenReturn(pad);
        return context;
    }

    private FindHandyman givenFindHandyman() {
        FindHandyman mock = Mockito.mock(FindHandyman.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), Mockito.anyString())).thenReturn(Mockito.mock(Handyman.class));
        return mock;
    }

    private FindPaddock givenFindPaddock() {
        FindPaddock mock = Mockito.mock(FindPaddock.class);
        when(mock.find(Mockito.any(Zoo.class), Mockito.anyString())).thenReturn(Mockito.mock(Paddock.class));
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenHandymanNorPaddockAreNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(mock(Handyman.class), mock(Paddock.class));
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenHandymanIsNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(null, mock(Paddock.class));
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenPaddockIsNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(mock(Handyman.class), null);
        FindPaddock findPaddock = givenFindPaddock();
        FindHandyman findHandyman = givenFindHandyman();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenHandymanAndPaddockAreNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(null, null);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
