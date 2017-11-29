package doyenm.zooshell.commandLine.commandLineImpl.keeper;

import doyenm.zooshell.commandLine.commandImpl.keeper.LsKeeper;
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
public class LsKeeperExecuteTest {

       private String[] givenCmd(){
         String[] cmd = {RandomStringUtils.randomAlphabetic(10)};
         return cmd;
     }
       
     private Zoo givenZoo() {
        return Mockito.mock(Zoo.class);
    }
    
    @Test
    public void shouldReturnAReturnExecWithSuccessAndANotEmptyString(){
        // Given
        String[] cmd = givenCmd();
        LsKeeper cmdImpl = new LsKeeper();
        // When
        ReturnExec returnExec = cmdImpl.execute(cmd, givenZoo());
        // Then
        Assertions.assertThat(returnExec).isNotNull();
        Assertions.assertThat(returnExec.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(returnExec.getMessage()).isNotNull();
    }
}
