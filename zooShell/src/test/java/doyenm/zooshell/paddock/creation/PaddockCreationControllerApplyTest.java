package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.model.*;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockCreationControllerApplyTest {

    private PaddockCreationContext givenContext(Zoo zoo, String name, int x, int y, int width, int height) {
        PaddockCreationContext context = Mockito.mock(PaddockCreationContext.class);
        when(context.getConvertedX()).thenReturn(x);
        when(context.getConvertedY()).thenReturn(y);
        when(context.getConvertedWidth()).thenReturn(width);
        when(context.getConvertedHeight()).thenReturn(height);
        when(context.getName()).thenReturn(name);
        when(context.getZoo()).thenReturn(zoo);
        return context;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        when(zoo.getPaddocks()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(zoo).setPaddocks(Mockito.anyMap());
        zoo.setPaddocks(new HashMap<>());
        return zoo;
    }

    @Test
    public void shouldAddToTheZooAPaddockWithTheExpectedValues() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        int x = RandomUtils.nextInt();
        int y = RandomUtils.nextInt();
        int width = RandomUtils.nextInt();
        int height = RandomUtils.nextInt();
        Zoo zoo = givenZoo();
        PaddockCreationContext context = givenContext(zoo, name, x, y, width, height);
        PaddockCreationController controller = new PaddockCreationController();
        // When
        PaddockCreationContext actual = controller.apply(context);
        // Then
        assertThat(actual.getZoo()).isNotNull();
        assertThat(actual.getZoo().getPaddocks()).isNotNull();
        assertThat(actual.getZoo().getPaddocks().size()).isEqualTo(1);
        assertThat(actual.getZoo().getPaddocks().containsKey(name.toUpperCase())).isTrue();
        Paddock pad = actual.getZoo().getPaddocks().get(name.toUpperCase());
        assertThat(pad.getAge()).isEqualTo(0);
        assertThat(pad.getX()).isEqualTo(x);
        assertThat(pad.getY()).isEqualTo(y);
        assertThat(pad.getWidth()).isEqualTo(width);
        assertThat(pad.getHeight()).isEqualTo(height);
        assertThat(pad.getName()).isEqualTo(name);
        assertThat(pad.getBiome()).isEqualTo(Biome.NONE);
        assertThat(pad.getType()).isEqualTo(PaddockType.UNKNOWN);
        assertThat(pad.getArrangements()).isNotNull();
        assertThat(pad.getArrangements()).hasSize(1);
        assertThat(pad.getArrangements().get(0)).isEqualTo(PaddockFacility.NONE);
    }
}
