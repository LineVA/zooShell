package doyenm.zooshell.animal.move;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class AnimalChangePaddockControllerApplyTest {

    private AnimalChangePaddockController subject;

    private Animal animal;
    private Paddock oldPaddock;
    private Paddock newPaddock;
    AnimalChangePaddockContext context;

    @Before
    public void setUp() {
        animal = mock(Animal.class);
        doCallRealMethod().when(animal).setPaddock(any(Paddock.class));
        when(animal.getPaddock()).thenCallRealMethod();
        String name = RandomStringUtils.randomAlphanumeric(10);
        when(animal.getName()).thenReturn(name);

        Zoo zoo = mock(Zoo.class);
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName(), animal);
        when(zoo.getAnimals()).thenReturn(map);

        oldPaddock = mock(Paddock.class);
        newPaddock = mock(Paddock.class);

        context = mock(AnimalChangePaddockContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedAnimal()).thenReturn(animal);
        when(context.getConvertedPaddock()).thenReturn(newPaddock);
        when(context.getAnimal()).thenReturn(name);

        subject = new AnimalChangePaddockController();
    }

    @Test
    public void shouldReplaceThePaddockOfTheAnimalByTheOneSpecified() {
        // Given
        String padName = RandomStringUtils.randomAlphabetic(10);
        when(oldPaddock.getName()).thenReturn(padName);
        // When
        AnimalChangePaddockContext actualContext = subject.apply(context);
        // Then
        assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        assertThat(actualContext.getZoo().getAnimals().size()).isEqualTo(1);
        assertThat(actualContext.getZoo().getAnimals().get(animal.getName()).getPaddock()).isEqualTo(newPaddock);

    }
}
