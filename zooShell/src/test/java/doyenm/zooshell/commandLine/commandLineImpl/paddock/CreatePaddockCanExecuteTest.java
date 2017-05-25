package doyenm.zooshell.commandLine.commandLineImpl.paddock;

import doyenm.zooshell.commandLine.commandImpl.paddock.CreatePaddock;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class CreatePaddockCanExecuteTest {

    private final String PADDOCK = "paddock";
    private final String PAD = "pad";
    private final String CREATE = "create";

    private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPaddock() {
        // Given
        Play play = givenPlay();
        CreatePaddock createPaddock = new CreatePaddock(play);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectAndeginsWithPad() {
        // Given
        Play play = givenPlay();
        CreatePaddock createPaddock = new CreatePaddock(play);
        String[] cmd = {this.PAD, this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheTheFirstElementIsNotPadOrPaddock() {
        // Given
        Play play = givenPlay();
        CreatePaddock createPaddock = new CreatePaddock(play);
        String falseAnimal = TestUtils.generateString();
        String[] cmd = {TestUtils.generateString(), this.CREATE, TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseAnimal).isNotEqualToIgnoringCase(PADDOCK);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheTheSecondElementIsNotCreate() {
        // Given
        Play play = givenPlay();
        CreatePaddock createPaddock = new CreatePaddock(play);
        String falseCreate = TestUtils.generateString();
        String[] cmd = {this.PADDOCK, falseCreate, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(falseCreate).isNotEqualToIgnoringCase(CREATE);
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsLessThanSevenElements() {
        // Given
        Play play = givenPlay();
        CreatePaddock CreatePaddock = new CreatePaddock(play);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString()};
        // When
        boolean actualResult = CreatePaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenThereIsMoreThanSevenElements() {
        // Given
        Play play = givenPlay();
        CreatePaddock createPaddock = new CreatePaddock(play);
        String[] cmd = {this.PADDOCK, this.CREATE, TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString(), TestUtils.generateString(), TestUtils.generateString(),
            TestUtils.generateString()};
        // When
        boolean actualResult = createPaddock.canExecute(cmd);
        // Then
        Assertions.assertThat(actualResult).isFalse();
    }

}
