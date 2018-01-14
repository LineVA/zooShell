package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.paddock.LsPaddockType;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsPaddockTypeExecuteTest {

    private Zoo givenZoo() {
        return Mockito.mock(Zoo.class);
    }

    private String[] givenCmd() {
        String[] cmd = {RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)};
        return cmd;
    }

    @Test
    public void shouldReturnAReturnExecWithSuccessAndANotEmptyString() {
        // Given
        String[] cmd = givenCmd();
        LsPaddockType cmdImpl = new LsPaddockType();
        // When
        ReturnExec returnExec = cmdImpl.execute(cmd, givenZoo());
        // Then
        Assertions.assertThat(returnExec).isNotNull();
        Assertions.assertThat(returnExec.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(returnExec.getMessage()).isNotEmpty();
    }
}
