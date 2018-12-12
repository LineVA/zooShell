package doyenm.zooshell.animal.rename;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameControllerApplyTest {

    private Animal givenAnimalWithName(String name) {
        Animal animal = mock(Animal.class);
        doCallRealMethod().when(animal).setName(anyString());
        Mockito.when(animal.getName()).thenCallRealMethod();
        animal.setName(name);
        return animal;
    }

    private Map<String, Animal> givenMapWithAnimal(Animal animal) {
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName().toUpperCase(), animal);
        return map;
    }

    private Zoo givenZooWithAnimals(Map<String, Animal> animals) {
        Zoo zoo = mock(Zoo.class);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalChangeNameContext givenContext(Zoo zoo, Animal animal, String newName) {
        AnimalChangeNameContext context = mock(AnimalChangeNameContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getNewName()).thenReturn(newName);
        String name = animal.getName();
        when(context.getCurrentName()).thenReturn(name);

        return context;
    }

    private AnimalChangeNameController subject;

    String oldName;
    String newName;
    Animal animal;
    AnimalChangeNameContext context;

    @Before
    public void setUp(){
        oldName = RandomStringUtils.randomAlphabetic(10);
        newName = RandomStringUtils.randomAlphabetic(10);
        assert newName != oldName;

        animal = mock(Animal.class);
        doCallRealMethod().when(animal).setName(anyString());
        when(animal.getName()).thenCallRealMethod();
        animal.setName(oldName);

        context = mock(AnimalChangeNameContext.class);
        Zoo zoo = mock(Zoo.class);
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName().toUpperCase(), animal);
        when(zoo.getAnimals()).thenReturn(map);

        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedAnimal()).thenReturn(animal);
        when(context.getNewName()).thenReturn(newName);
        when(context.getCurrentName()).thenReturn(oldName);

        subject = new AnimalChangeNameController();
    }

    @Test
    public void shouldReplaceTheNameOfTheAnimalByTheOneSpecified() {
        // Given
        // When
        AnimalChangeNameContext actualContext = subject.apply(context);
        // Then
        assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        assertThat(actualContext.getZoo().getAnimals()).hasSize(1);
        assertThat(actualContext.getZoo().getAnimals().get(oldName.toUpperCase())).isNull();
        assertThat(actualContext.getZoo().getAnimals().get(newName.toUpperCase())).isNotNull();

    }
}
