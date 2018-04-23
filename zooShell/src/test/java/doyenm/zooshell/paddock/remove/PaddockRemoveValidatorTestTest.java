package doyenm.zooshell.paddock.remove;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.remove.PaddockRemoveValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveValidatorTestTest {

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        return pad;
    }

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private PaddockContext givenContextWithZooAnimalsAndPad(
            Zoo zoo, Collection<Animal> animals, Paddock pad) {
        PaddockContext context = Mockito.mock(PaddockContext.class);
        Mockito.when(context.getPaddock()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Mockito.when(context.getAnimals()).thenReturn(animals);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        return context;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        return zoo;
    }

    private FindPaddock givenFindWithPad(Paddock pad) {
        FindPaddock find = Mockito.mock(FindPaddock.class);
        Mockito.doReturn(pad).when(find).find(Mockito.any(Zoo.class), Mockito.anyString());
        return find;
    }

    @Test
    public void shouldReturnTrueIfThePaddockExistsAndIsEmpty() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        List<Animal> animals = new ArrayList<>();
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, pad);
        PaddockRemoveValidator validator = new PaddockRemoveValidator(find);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfThePaddockDoesNotExist() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(null);
        List<Animal> animals = new ArrayList<>();
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, pad);
        PaddockRemoveValidator validator = new PaddockRemoveValidator(find);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseIfThePaddockExistsButIsNotEmpty() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        Animal animal = givenAnimalWithPaddock(pad);
        List<Animal> animals = Arrays.asList(animal);
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, pad);
        PaddockRemoveValidator validator = new PaddockRemoveValidator(find);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
