package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.model.*;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;

/**
 *
 * @author doyenm
 */
public class AnimalCreationValidatorTestTest {

    private Specie givenSpecie() {
        return Mockito.mock(Specie.class);
    }

    private Position givenPosition() {
        return Mockito.mock(Position.class);
    }

    private Paddock givenPaddockWithEntry(Position entry, int turns) {
        Paddock pad = Mockito.mock(Paddock.class);
        Mockito.when(pad.getEntry()).thenReturn(entry);
        Mockito.when(pad.getTurnsOfUnusableState()).thenReturn(turns);
        return pad;
    }

    private Animal givenAnimalWithPaddock(Paddock pad) {
        Animal animal = Mockito.mock(Animal.class);
        Mockito.when(animal.getPaddock()).thenReturn(pad);
        return animal;
    }

    private AnimalCreationContext givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
            Sex sex, Specie specie, Paddock pad, Animal animal, String name) {
        AnimalCreationContext context = Mockito.mock(AnimalCreationContext.class);
        Mockito.when(context.getName()).thenReturn(name);
        Mockito.when(context.getSex()).thenReturn(sex);
        Mockito.doCallRealMethod().when(context).setSex(Mockito.any(Sex.class));
        Mockito.when(context.getSexName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getPaddock()).thenReturn(pad);
        Mockito.doCallRealMethod().when(context).setPaddock(Mockito.any(Paddock.class));
        Mockito.when(context.getPaddockName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getSpecie()).thenReturn(specie);
        Mockito.doCallRealMethod().when(context).setSpecie(Mockito.any(Specie.class));
        Mockito.when(context.getSpecieName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getAnimalsList()).thenReturn(new ArrayList<>());
        return context;
    }

    private FindPaddock givenFindPaddock(Paddock pad) {
        FindPaddock mock = Mockito.mock(FindPaddock.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), anyString())).thenReturn(pad);
        return mock;
    }

    /**
     * An animal can be created if : - the name is not empty - the name is
     * shorter than 50 characters - the paddock exists - the paddock has an
     * entry - the specie exists - the sex exists - the sex is not UNKNOWN
     */
    @Test
    public void shouldReturnTrueWhenTheAnimalCanBeCreated() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNameIsEmpty() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = "";
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNameIsTooLong() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(51);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 50);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Paddock convertedPaddock = null;
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotHaveAnEntry() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = null;
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSpecieDoesNotExist() {
        // Given
        Specie convertedSpecie = null;
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.FEMALE,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSexDoesNotExist() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                null,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheSexIsUnknown() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 1);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.UNKNOWN,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThePadIsUnusableSinceThreeTurns() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 3);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.UNKNOWN,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnFalseWhenThePadIsUnusableSinceMoreThanThreeTurns() {
        // Given
        Specie convertedSpecie = givenSpecie();
        Position entry = givenPosition();
        Paddock convertedPaddock = givenPaddockWithEntry(entry, 6);
        String animalName = RandomStringUtils.randomAlphabetic(10);
        Animal convertedAnimal = givenAnimalWithPaddock(convertedPaddock);
        AnimalCreationContext context = givenContextWithConvertedSexSpeciePaddockAnimalAndAnimalName(
                Sex.UNKNOWN,
                convertedSpecie,
                convertedPaddock,
                convertedAnimal,
                animalName
        );
        AnimalCreationValidator validator = new AnimalCreationValidator(givenFindPaddock(convertedPaddock), 3, 11);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
