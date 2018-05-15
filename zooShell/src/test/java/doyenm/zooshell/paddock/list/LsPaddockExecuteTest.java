package doyenm.zooshell.paddock.list;

import doyenm.zooshell.paddock.list.LsPaddock;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

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
