package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 *
 * @author doyenm
 */
public class LsPaddockArrangementsExecuteTest {

    private LsPaddockArrangements subject;

    private String[] cmd = new String[1];


    @Before
    public void setUp(){
        subject = new LsPaddockArrangements();
    }

    @Test
    public void shouldReturnAReturnExecWithTypeReturnToSuccess() {
        // Given
        // When
        ReturnExec result = subject.execute(cmd, mock(Zoo.class));
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTypeReturn()).isEqualTo(TypeReturn.SUCCESS);
        assertThat(result.getMessage()).isNotEmpty();
    }
}
