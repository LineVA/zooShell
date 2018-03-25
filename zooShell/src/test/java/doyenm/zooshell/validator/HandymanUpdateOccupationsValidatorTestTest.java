package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanUpdateOccupationsContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class HandymanUpdateOccupationsValidatorTestTest {

    private HandymanUpdateOccupationsContext givenContext(Handyman handyman, Paddock pad, boolean isAddition) {
        HandymanUpdateOccupationsContext context = Mockito.mock(HandymanUpdateOccupationsContext.class);
        when(context.getHandymanName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getPaddockName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getHandyman()).thenReturn(handyman);
        when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        when(context.getPaddock()).thenReturn(pad);
        when(context.isAddition()).thenReturn(isAddition);
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

    private Handyman givenHandyman(List<Paddock> pads) {
        Handyman mock = Mockito.mock(Handyman.class);
        when(mock.getAffectations()).thenReturn(pads);
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenHandymanNorPaddockAreNullAndAdditionIsAuthorized() {
        // Given
        Handyman handyman = givenHandyman(new ArrayList<>());
        HandymanUpdateOccupationsContext context = givenContext(handyman, mock(Paddock.class), true);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenAdditionIsUnauthorizedBecauseTheyHaveTooManyAffectations() {
        // Given
        Handyman handyman = givenHandyman(Arrays.asList(mock(Paddock.class),
                mock(Paddock.class),
                mock(Paddock.class),
                mock(Paddock.class),
                mock(Paddock.class)));
        HandymanUpdateOccupationsContext context = givenContext(handyman, mock(Paddock.class), true);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenAdditionIsUnauthorizedBecauseTheyAlreadyAreAffectedToThisPaddock() {
        // Given
        Paddock pad = mock(Paddock.class);
        Handyman handyman = givenHandyman(Arrays.asList(pad));
        HandymanUpdateOccupationsContext context = givenContext(handyman, pad, true);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenHandymanNorPaddockAreNullAndSubstractionIsAuthorized() {
        // Given
        Paddock pad = mock(Paddock.class);
        Handyman handyman = givenHandyman(Arrays.asList(pad));
        HandymanUpdateOccupationsContext context = givenContext(handyman, pad, false);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenSubstractionIsUnauthorizedBecauseTheyAreNotAlreadyAreAffectedToThisPaddock() {
        // Given
        Paddock pad = mock(Paddock.class);
        Handyman handyman = givenHandyman(Arrays.asList());
        HandymanUpdateOccupationsContext context = givenContext(handyman, pad, false);
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }


    @Test
    public void shouldReturnFalseWhenHandymanIsNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(null, mock(Paddock.class), RandomUtils.nextBoolean());
        FindHandyman findHandyman = givenFindHandyman();
        FindPaddock findPaddock = givenFindPaddock();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenPaddockIsNull() {
        // Given
        HandymanUpdateOccupationsContext context = givenContext(mock(Handyman.class), null, RandomUtils.nextBoolean());
        FindPaddock findPaddock = givenFindPaddock();
        FindHandyman findHandyman = givenFindHandyman();
        HandymanUpdateOccupationsValidator validator = new HandymanUpdateOccupationsValidator(
                findHandyman, findPaddock, 5);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
