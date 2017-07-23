package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddockEntry;
import doyenm.zooshell.testUtils.TestUtils;
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
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()
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
        String[] cmd = {this.PAD, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()
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
        String falseAnimal = TestUtils.generateString();
        String[] cmd = {TestUtils.generateString(), this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()
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
        String falseCreate = TestUtils.generateString();
        String[] cmd = {this.PADDOCK, falseCreate, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
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
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanFiveElements() {
        // Given
        CreatePaddockEntry createPaddock = new CreatePaddockEntry(null, null);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()
           };
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
