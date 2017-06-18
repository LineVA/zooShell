package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsDiet;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsDietExecuteTest {

    private String[] givenCmd() {
        String[] cmd = {TestUtils.generateString(), TestUtils.generateString()};
        return cmd;
    }
    
    private Zoo givenZoo() {
        return Mockito.mock(Zoo.class);
    }

    @Test
    public void shouldReturnAReturnExecWithSuccessAndANotEmptyString() {
        // Given
        String[] cmd = givenCmd();
        LsDiet cmdImpl = new LsDiet();
        // When
        ReturnExec returnExec = cmdImpl.execute(cmd, givenZoo());
        // Then
        Assertions.assertThat(returnExec).isNotNull();
        Assertions.assertThat(returnExec.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(returnExec.getMessage()).isNotEmpty();
    }
}
