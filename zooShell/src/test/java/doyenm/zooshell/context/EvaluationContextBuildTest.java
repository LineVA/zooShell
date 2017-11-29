package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class EvaluationContextBuildTest {

    private Animal givenAnimalWithName(String name) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getName()).thenReturn(name);
        return animal;
    }
    
    private Paddock givenPaddockWithName(String name) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getName()).thenReturn(name);
        return pad;
    }

    private Animal givenAnimalWithNameAndPaddock(String name, Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getName()).thenReturn(name);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
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

    private EvaluationContext givenContextWithZooAndEvaluatedList(Zoo zoo, List<Animal> evaluatedList) {
        EvaluationContext context = Mockito.mock(EvaluationContext.class);
        Mockito.doCallRealMethod().when(context).updateAnimals();
        Mockito.when(context.getAnimals()).thenCallRealMethod();
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getEvaluatedAnimalsList()).thenReturn(evaluatedList);
        Mockito.when(context.getNewBornsList()).thenReturn(new ArrayList<>());
        return context;
    }

    @Test
    public void shouldDeleteTheAnimalOfTheZooWhenItIsNoMorePresentAfterEvaluation() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimalWithName(name);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        List<Animal> evaluatedList = new ArrayList<>();
        Zoo zoo = givenZooWithAnimals(animals);
        EvaluationContext context = givenContextWithZooAndEvaluatedList(zoo, evaluatedList);
        // When
        context.updateAnimals();
        // Then
        Assertions.assertThat(context.getAnimals()).isNotNull();
        Assertions.assertThat(context.getAnimals().size()).isEqualTo(0);
    }

    @Test
    public void shouldUpdateTheAnimalOfTheZooWhenItIsStillPresentAfterEvaluation() {
        // Given
        String animalName = RandomStringUtils.randomAlphabetic(10);
        String padName = RandomStringUtils.randomAlphabetic(10);
        Paddock paddock = givenPaddockWithName(padName);
        Animal animal = givenAnimalWithNameAndPaddock(animalName, paddock);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        List<Animal> evaluatedList = new ArrayList<>();
        Zoo zoo = givenZooWithAnimals(animals);
        EvaluationContext context = givenContextWithZooAndEvaluatedList(zoo, evaluatedList);
        // When
        context.updateAnimals();
        // Then
        Assertions.assertThat(context.getAnimals()).isNotNull();
        Assertions.assertThat(context.getAnimals().size()).isEqualTo(0);
    }

}
