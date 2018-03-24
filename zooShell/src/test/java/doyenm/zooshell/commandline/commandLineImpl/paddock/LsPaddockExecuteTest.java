package doyenm.zooshell.commandline.commandLineImpl.paddock;

import doyenm.zooshell.commandline.commandImpl.paddock.LsPaddock;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class LsPaddockExecuteTest {

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getPaddocks()).thenReturn(new HashMap<>());
        return zoo;
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccess() {
        // Given
        Zoo zoo = givenZoo();
        LsPaddock command = new LsPaddock();
        String[] cmd = new String[4];
        // When
        ReturnExec result = command.execute(cmd, zoo);
        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        Assertions.assertThat(result.getMessage()).isNotNull();
        Assertions.assertThat(result.getZoo()).isNull();
    }

}
