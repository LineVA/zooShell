package doyenm.zooshell.commandline.commandLineImpl;

import doyenm.zooshell.zoo.LsPenalties;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsPenaltiesExecuteTest {

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
        LsPenalties cmdImpl = new LsPenalties();
        // When
        ReturnExec returnExec = cmdImpl.execute(cmd, givenZoo());
        // Then
        Assertions.assertThat(returnExec).isNotNull();
        Assertions.assertThat(returnExec.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(returnExec.getMessage()).isNotNull();
    }
}
