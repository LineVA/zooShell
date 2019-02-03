package doyenm.zooshell.animal.move;

import doyenm.zooshell.common.FindAnimal;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class AnimalChangePaddockValidatorTest {

    private AnimalChangePaddockValidator subject;

    private FindAnimal findAnimal;
    private FindPaddock findPaddock;

    private AnimalChangePaddockContext context;
    private Paddock paddock;

    @Before
    public void setUp() {
        paddock = mock(Paddock.class);

        context = mock(AnimalChangePaddockContext.class);
        doCallRealMethod().when(context).setConvertedAnimal(any());
        doCallRealMethod().when(context).setConvertedPaddock(any());
        when(context.getConvertedAnimal()).thenCallRealMethod();
        when(context.getConvertedPaddock()).thenCallRealMethod();

        findAnimal = mock(FindAnimal.class);
        findPaddock = mock(FindPaddock.class);

        subject = new AnimalChangePaddockValidator(findAnimal, findPaddock);
    }


    /**
     * An animal can change its paddock if : - the new paddock exists and is usable - the
     * animal exists
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeMoved() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(mock(Animal.class));
        when(findPaddock.find(any(Zoo.class), anyString())).thenReturn(paddock);
        when(paddock.getTurnsOfUnusableState()).thenReturn(2);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(null);
        when(findPaddock.find(any(Zoo.class), anyString())).thenReturn(paddock);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(mock(Animal.class));
        when(findPaddock.find(any(Zoo.class), anyString())).thenReturn(null);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockIsUnusableSinceThreeTurns() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(mock(Animal.class));
        when(findPaddock.find(any(Zoo.class), anyString())).thenReturn(paddock);
        when(paddock.getTurnsOfUnusableState()).thenReturn(3);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockIsUnusableSinceMoreThanThreeTurns() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(mock(Animal.class));
        when(findPaddock.find(any(Zoo.class), anyString())).thenReturn(paddock);
        when(paddock.getTurnsOfUnusableState()).thenReturn(4);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

}
