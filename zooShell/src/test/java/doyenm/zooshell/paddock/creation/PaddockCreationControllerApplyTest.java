package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.paddock.creation.PaddockCreationContext;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.creation.PaddockCreationController;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

/**
 *
 * @author doyenm
 */
public class PaddockCreationControllerApplyTest {

    private PaddockCreationContext givenContext(Zoo zoo, String name, int x, int y, int width, int height) {
        PaddockCreationContext context = Mockito.mock(PaddockCreationContext.class);
        Mockito.when(context.getConvertedX()).thenReturn(x);
        Mockito.when(context.getConvertedY()).thenReturn(y);
        Mockito.when(context.getConvertedWidth()).thenReturn(width);
        Mockito.when(context.getConvertedHeight()).thenReturn(height);
        Mockito.when(context.getName()).thenReturn(name);
        Mockito.when(context.getZoo()).thenReturn(zoo);
//        Mockito.doCallRealMethod().when(context).setZoo(Mockito.any(Zoo.class));
//        context.(zoo);
        return context;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getPaddocks()).thenCallRealMethod();
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
        Assertions.assertThat(actual.getZoo()).isNotNull();
        Assertions.assertThat(actual.getZoo().getPaddocks()).isNotNull();
        Assertions.assertThat(actual.getZoo().getPaddocks().size()).isEqualTo(1);
        Assertions.assertThat(actual.getZoo().getPaddocks().containsKey(name.toUpperCase())).isTrue();
        Paddock pad = actual.getZoo().getPaddocks().get(name.toUpperCase());
        Assertions.assertThat(pad.getAge()).isEqualTo(0);
        Assertions.assertThat(pad.getX()).isEqualTo(x);
        Assertions.assertThat(pad.getY()).isEqualTo(y);
        Assertions.assertThat(pad.getWidth()).isEqualTo(width);
        Assertions.assertThat(pad.getHeight()).isEqualTo(height);
        Assertions.assertThat(pad.getName()).isEqualTo(name);
        Assertions.assertThat(pad.getBiome()).isEqualTo(Biome.NONE);
        Assertions.assertThat(pad.getType()).isEqualTo(PaddockType.UNKNOWN);
    }
}
