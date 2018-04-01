package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.model.Animal;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PresenceIfAnimalsPredicateTestTest {

    PresenceOfAnimalsPredicate subject;

    @Before
    public void setUp(){
        subject = new PresenceOfAnimalsPredicate();
    }

    @Test
    public void shouldReturnTrueWhenThereIsMoreThanZeroAnimalsInTheZoo(){
        // Given
        int i = RandomUtils.nextInt();
        i = i == 0 ? i : 1;
        KeeperCreationContext context = genContext(i);

        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThereIsNoAnimalsInTheZoo(){
        // Given
        KeeperCreationContext context = genContext(0);

        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanZeroAnimalsInTheZoo(){
        // Given
        int i = RandomUtils.nextInt();
        i = i == 0 ? i : 1;
        KeeperCreationContext context = genContext(0 - i);

        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isFalse();
    }


    private KeeperCreationContext genContext(int numberOfAnimals){
        KeeperCreationContext mock = mock(KeeperCreationContext.class);
        List<Animal> animals = new ArrayList<>();
        for(int i = 0 ; i< numberOfAnimals ; i++){
            animals.add(mock(Animal.class));
        }
        when(mock.getAnimals()).thenReturn(animals);
        return mock;
    }
}
