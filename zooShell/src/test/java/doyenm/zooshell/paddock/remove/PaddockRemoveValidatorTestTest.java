package doyenm.zooshell.paddock.remove;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.remove.PaddockRemoveValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveValidatorTestTest {

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = mock(Animal.class);
        when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private FindPaddock givenFindWithPad(Paddock pad) {
        FindPaddock find = mock(FindPaddock.class);
        Mockito.doReturn(pad).when(find).find(Mockito.any(Zoo.class), Mockito.anyString());
        return find;
    }

    private PaddockRemoveValidator subject;

    private FindPaddock findPaddock;
    private Paddock paddock;
    private List<Animal> animals;
    private Zoo zoo;

    @Before
    public void setUp(){
        findPaddock = mock(FindPaddock.class);
        paddock = mock(Paddock.class);
        zoo = mock(Zoo.class);
        subject = new PaddockRemoveValidator(findPaddock);
    }

    @Test
    public void shouldReturnTrueIfThePaddockExistsAndIsEmpty() {
        // Given
        when(findPaddock.find(any(), any())).thenReturn(paddock);
        animals = new ArrayList<>();
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, paddock);
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseIfThePaddockDoesNotExist() {
        // Given
        when(findPaddock.find(any(), any())).thenReturn(null);
        animals = new ArrayList<>();
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, paddock);
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseIfThePaddockExistsButIsNotEmpty() {
        // Given
        when(findPaddock.find(any(), any())).thenReturn(paddock);
        Animal animal = givenAnimalWithPaddock(paddock);
        List<Animal> animals = Arrays.asList(animal);
        PaddockContext context = givenContextWithZooAnimalsAndPad(zoo, animals, paddock);
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    private PaddockContext givenContextWithZooAnimalsAndPad(
            Zoo zoo, Collection<Animal> animals, Paddock pad) {
        PaddockContext context = mock(PaddockContext.class);
        when(context.getPaddock()).thenReturn(RandomStringUtils.random(10));
        when(context.getConvertedPaddock()).thenReturn(pad);
        when(context.getAnimals()).thenReturn(animals);
        when(context.getZoo()).thenReturn(zoo);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        return context;
    }
}
