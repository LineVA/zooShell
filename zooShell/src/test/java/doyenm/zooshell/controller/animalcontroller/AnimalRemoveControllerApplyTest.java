package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class AnimalRemoveControllerApplyTest {

    private Zoo givenZooWithNames(String... names) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Animal> map = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            map.put(names[i].toUpperCase(), Mockito.mock(Animal.class));
        }
        Mockito.doCallRealMethod().when(zoo).setAnimals(Mockito.anyMap());
        zoo.setAnimals(map);
        Mockito.when(zoo.getAnimals()).thenCallRealMethod();
        return zoo;
    }

    private AnimalContext givenContextWithZooAndName(Zoo zoo, String name) {
        AnimalContext context = Mockito.mock(AnimalContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getAnimal()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldRemoveTheAnimalOfTheMap() {
        // Given
        String name1 = RandomStringUtils.randomAlphabetic(10);
        String name2 = RandomStringUtils.randomAlphabetic(10);
        Zoo zoo = givenZooWithNames(name1, name2);
        AnimalContext context = givenContextWithZooAndName(zoo, name1);
        AnimalRemoveController controller = new AnimalRemoveController();
        // When
        AnimalContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(name1).isNotEqualToIgnoringCase(name2);
        Assertions.assertThat(actualContext.getZoo().getAnimals().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getAnimals().containsKey(name1)).isFalse();
    }
}
