package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Zoo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionControllerApplyTest {

    private AnimalUpdateContraceptionController subject;

    private Animal givenAnimal(String name, ContraceptionMethod method) {
        Animal animal = Mockito.mock(Animal.class);
        doCallRealMethod().when(animal).setContraceptionMethod(any());
        when(animal.getContraceptionMethod()).thenCallRealMethod();
        animal.setContraceptionMethod(method);
        when(animal.getName()).thenReturn(name);
        return animal;
    }

    private Map<String, Animal> givenMapWithAnimal(Animal animal) {
        Map<String, Animal> map = new HashMap<>();
        map.put(animal.getName().toUpperCase(), animal);
        return map;
    }

    private Zoo givenZooWithAnimals(Map<String, Animal> animals) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getAnimals()).thenReturn(animals);
        return zoo;
    }

    private AnimalUpdateContraceptionContext givenContext(Zoo zoo, Animal animal, ContraceptionMethod method) {
        AnimalUpdateContraceptionContext context = Mockito.mock(AnimalUpdateContraceptionContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedAnimal()).thenReturn(animal);
        String name = animal.getName();
        when(context.getAnimal()).thenReturn(name);
        when(context.getConvertedContraceptionMethod()).thenReturn(method);
        return context;
    }

    @Test
    public void shouldReplaceTheContraceptionMethodOfTheAnimal() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Animal animal = givenAnimal(name, ContraceptionMethod.CASTRACTION);
        Map<String, Animal> animals = givenMapWithAnimal(animal);
        Zoo zoo = givenZooWithAnimals(animals);
        AnimalUpdateContraceptionContext context = givenContext(zoo, animal, ContraceptionMethod.FEMALE_IMPLANT);
        subject = new AnimalUpdateContraceptionController();
        // When
        AnimalUpdateContraceptionContext actualContext = subject.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getAnimals()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getAnimals()
                .get(name.toUpperCase()).getContraceptionMethod()).isEqualTo(ContraceptionMethod.FEMALE_IMPLANT);

    }
}
