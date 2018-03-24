package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandimpl.paddock.CreatePaddockEntry;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 *
 * @author doyenm
 */
public class CreatePaddockEntryCanExecuteTest {

    private final String PADDOCK = "paddock-entry";
    private final String PAD = "pad-entry";
    private final String CREATE = "create";

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPaddock() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)
            };
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndBeginsWithPad() {
        // Given
             CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String[] cmd = {this.PAD, this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)
      };
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddockEntry() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String falseAnimal = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), this.CREATE, RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)
         };
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(PADDOCK);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String falseCreate = RandomStringUtils.randomAlphabetic(10);
        String[] cmd = {this.PADDOCK, falseCreate, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanFiveElements() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFiveElements() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10),
            RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)
           };
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
