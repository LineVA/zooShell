package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalChangePaddockControllerApplyTest {

    private Animal givenAnimalWithName(String name) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.doCallRealMethod().when(animal).setPaddock(Mockito.any(Paddock.class));
        Mockito.when(animal.getPaddock()).thenCallRealMethod();
        Mockito.when(animal.getName()).thenReturn(name);
        return animal;
    }
    
     private Paddock givenPaddockWithName(String name) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getName()).thenReturn(name);
        return pad;
    }

    private Map<String, Animal> givenMapWithAnimal(Animal animal) {
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName(), animal);
        return map;
    }

    private Zoo givenZooWithAnimals(Map<String, Animal> animals) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalChangePaddockContext givenContextWithZooAnimalAndPaddock(Zoo zoo, Animal animal, Paddock pad) {
        AnimalChangePaddockContext context = Mockito.mock(AnimalChangePaddockContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        return context;
    }

    @Test
    public void shouldReplaceThePaddockOfTheAnimalByTheOneSpecified() {
        // Given
        String name = TestUtils.generateString();
        Animal animal = givenAnimalWithName(name);
        String padName = TestUtils.generateString();
        Paddock newPaddock = givenPaddockWithName(padName);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalChangePaddockContext context = givenContextWithZooAnimalAndPaddock(zoo, animal, newPaddock);
        AnimalChangePaddockController controller = new AnimalChangePaddockController();
        // When
       AnimalChangePaddockContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getAnimals().get(name).getPaddock()).isEqualTo(newPaddock);

    }
}
