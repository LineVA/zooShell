package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.mockito.Mockito;

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
        Mockito.when(context.getZoo()).thenCallRealMethod();
//        Mockito.doCallRealMethod().when(context).setZoo(Mockito.any(Zoo.class));
//        context.(zoo);
        return context;
    }

//    @Test
//    public void shouldAddToTheZooAPaddcokWithTheExpectedValues() {
//        // Given
//        String name = RandomStringUtils.random(10);
//        int x = RandomUtils.nextInt();
//        int y = RandomUtils.nextInt();
//        int width = RandomUtils.nextInt();
//        int height = RandomUtils.nextInt();
//        PaddockCreationContext context = givenContext(name, x, y, width, height);
//        PaddockCreationController controller = new PaddockCreationController();
//        // When
//        // Then
//    }
}
