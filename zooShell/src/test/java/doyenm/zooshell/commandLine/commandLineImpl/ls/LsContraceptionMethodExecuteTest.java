package doyenm.zooshell.commandLine.commandLineImpl.ls;

import doyenm.zooshell.commandLine.commandImpl.ls.LsContraceptionMethod;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.testUtils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsContraceptionMethodExecuteTest {

     private Play givenPlay() {
        Play play = Mockito.mock(Play.class);
        return play;
    }
     
     private String[] givenCmd(){
         String[] cmd = {TestUtils.generateString(), TestUtils.generateString()};
         return cmd;
     }
    
    @Test
    public void shouldReturnAReturnExecWithSuccessAndANotEmptyString(){
        // Given
        String[] cmd = givenCmd();
        LsContraceptionMethod cmdImpl = new LsContraceptionMethod(givenPlay());
        // When
        ReturnExec returnExec = cmdImpl.execute(cmd);
        // Then
        Assertions.assertThat(returnExec).isNotNull();
        Assertions.assertThat(returnExec.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(returnExec.getMessage()).isNotEmpty();
    }
}
