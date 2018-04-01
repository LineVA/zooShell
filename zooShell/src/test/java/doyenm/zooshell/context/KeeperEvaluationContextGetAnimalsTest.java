package doyenm.zooshell.context;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationContextGetAnimalsTest {

    @Test
    public void shouldReturnTheAnimalsOfTheZooOfTheContext() {
        // Given
       Map<String, Animal> animals
                = new ImmutableMap.Builder<String, Animal>()
                        .put(RandomStringUtils.randomAlphanumeric(10), mock(Animal.class))
                        .build();
        Zoo zoo = mock(Zoo.class);
        when(zoo.getAnimals()).thenReturn(animals);
        KeeperEvaluationContext subject = new KeeperEvaluationContext(zoo,
                mock(AnimalKeeper.class));
        // When
        List<Animal> actualAnimals = subject.getAnimals();
        // Then
        Assertions.assertThat(actualAnimals).hasSize(1);
    }
}
