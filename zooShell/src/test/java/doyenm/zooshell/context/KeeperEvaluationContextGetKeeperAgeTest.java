package doyenm.zooshell.context;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetKeeperAgeTest {

    @Test
    public void shouldReturnTheAgeOfTheKeeper() {
        // Given
        int age = RandomUtils.nextInt();
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        when(keeper.getAge()).thenReturn(age);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class),
                keeper);
        // When
        int actualAge = subject.getKeeperAge();
        // Then
        Assertions.assertThat(actualAge).isEqualTo(age);
    }

}
