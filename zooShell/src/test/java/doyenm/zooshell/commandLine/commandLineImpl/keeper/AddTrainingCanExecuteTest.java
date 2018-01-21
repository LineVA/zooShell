package doyenm.zooshell.commandLine.commandLineImpl.keeper;

import doyenm.zooshell.commandLine.commandImpl.keeper.AddTraining;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class AddTrainingCanExecuteTest {

    private final String ANIMAL_KEEPER_TRAINING = "animalKeeper-training";
    private final String AK_TRAINING = "ak-training";
    private final String UPDATE = "update";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAnimalKeeper() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {this.ANIMAL_KEEPER_TRAINING, this.UPDATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsByAK() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {this.AK_TRAINING, this.UPDATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsIncorrect() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.UPDATE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsIncorrect() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {this.AK_TRAINING, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFourElements() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {this.AK_TRAINING, this.UPDATE, RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFourElements() {
        // Given
        AddTraining commandImpl = new AddTraining(null, null);
        String[] cmd = {this.AK_TRAINING, this.UPDATE,
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = commandImpl.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }
}
