package doyenm.zooshell.animal.rename;

import doyenm.zooshell.common.FindAnimal;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class AnimalRenameValidatorTestTest {

    private AnimalRenameValidator subject;

    FindAnimal findAnimal;
    NameValidator nameValidator;

    Animal animal;
    AnimalRenameContext context;

    @Before
    public void setUp(){

        animal = mock(Animal.class);

        context = mock(AnimalRenameContext.class);
        when(context.getNewName()).thenReturn(RandomStringUtils.random(10));
        when(context.getCurrentName()).thenReturn(RandomStringUtils.random(10));
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getAnimals()).thenReturn(new HashSet<>());
        doCallRealMethod().when(context).setConvertedAnimal(Mockito.any(Animal.class));

        findAnimal = mock(FindAnimal.class);
        nameValidator = mock(NameValidator.class);
        subject = new AnimalRenameValidator(findAnimal, nameValidator);
    }

    /**
     * An animal can be renamed if : - the new paddock has an entry - the animal
     * exists - the new name is shorter than 50 characters - the new name is
     * unique
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeRenamed() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(animal);
        when(nameValidator.test(any(NameDto.class))).thenReturn(true);
        // When
        boolean result = subject.test(context);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalDoesNotExist() {
        // Given
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(null);
        when(nameValidator.test(any(NameDto.class))).thenReturn(true);
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsKO() {
        // Given
        when(findAnimal.find(any(Zoo.class), anyString())).thenReturn(animal);
        when(nameValidator.test(any(NameDto.class))).thenReturn(false);
        // When
        boolean result = subject.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
