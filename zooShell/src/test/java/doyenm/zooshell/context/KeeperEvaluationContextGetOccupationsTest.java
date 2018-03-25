package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetOccupationsTest {

    @Test
    public void shouldReturnTheOccupationsOfTheKeeper() {
        // Given
        List<TimedOccupation> occupations = Arrays.asList(TimedOccupation.builder().build());
        AnimalKeeper keeper = mock(AnimalKeeper.class);
        when(keeper.getOccupations()).thenReturn(occupations);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(mock(Zoo.class),
                keeper);
        // When
        List<TimedOccupation> actualOccupations = subject.getOccupations();
        // Then
        Assertions.assertThat(actualOccupations).hasSize(1);
        Assertions.assertThat(actualOccupations.get(0)).isEqualTo(occupations.get(0));
    }

}
