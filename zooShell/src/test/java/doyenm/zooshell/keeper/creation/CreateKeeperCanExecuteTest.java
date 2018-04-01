package doyenm.zooshell.keeper.creation;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CreateKeeperCanExecuteTest {

    private static final String AK = "ak";
    private static final String ANIMAL_KEEPER= "animalkeeper";
    private static final String CREATE = "create";

    private CreateKeeper subject;

    @Before
    public void setUp(){
        subject = new CreateKeeper(null, null);
    }

    @Test
    public void shouldReturnTrueWhenTheCommandLineIsCorrectAndBeginsByAk(){
        // Given
        String[] cmd = {AK, CREATE, RandomStringUtils.randomAlphabetic(5)};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandLineIsCorrectAndBeginsByAnimalKeeper(){
        // Given
        String[] cmd = {ANIMAL_KEEPER, CREATE, RandomStringUtils.randomAlphabetic(5)};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandLineDoesNotBeginByAKOrAnimalKeeper(){
        // Given
        String[] cmd = {RandomStringUtils.randomAlphabetic(5), CREATE, RandomStringUtils.randomAlphabetic(5)};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandLineDoesNotHaveCreateAsSecondElement(){
        // Given
        String[] cmd = {ANIMAL_KEEPER, RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandLineIsTooShort(){
        // Given
        String[] cmd = {ANIMAL_KEEPER, CREATE};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCommandLineISTooLong(){
        // Given
        String[] cmd = {ANIMAL_KEEPER, CREATE, RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5)};
        // When
        boolean result = subject.canExecute(cmd);
        // Then
        assertThat(result).isFalse();
    }
}
