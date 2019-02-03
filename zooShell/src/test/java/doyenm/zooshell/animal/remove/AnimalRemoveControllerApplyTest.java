package doyenm.zooshell.animal.remove;

import doyenm.zooshell.animal.AnimalContext;
import doyenm.zooshell.animal.remove.AnimalRemoveController;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class AnimalRemoveControllerApplyTest {

    private AnimalRemoveController subject;

    private String name;
    private String otherName;
    private Zoo zoo;
    AnimalContext context;

    @Before
    public void setUp(){
        name = RandomStringUtils.randomAlphanumeric(10);
        otherName = RandomStringUtils.randomAlphanumeric(10);
        assert  name != otherName;

        zoo = mock(Zoo.class);
        Map<String, Animal> map = new HashMap<>();
        map.put(name.toUpperCase(), mock(Animal.class));
        map.put(otherName.toUpperCase(), mock(Animal.class));

        doCallRealMethod().when(zoo).setAnimals(anyMap());
        zoo.setAnimals(map);
        when(zoo.getAnimals()).thenCallRealMethod();

        context = mock(AnimalContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getAnimal()).thenReturn(name);

        subject = new AnimalRemoveController();
    }

    @Test
    public void shouldRemoveTheAnimalOfTheMap() {
        // Given
        // When
        AnimalContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(name).isNotEqualToIgnoringCase(otherName);
        Assertions.assertThat(actualContext.getZoo().getAnimals()).hasSize(1);
        Assertions.assertThat(actualContext.getZoo().getAnimals().containsKey(name.toUpperCase())).isFalse();
    }
}
