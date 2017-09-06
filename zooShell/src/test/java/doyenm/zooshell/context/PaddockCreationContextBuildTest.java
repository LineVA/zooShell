//package doyenm.zooshell.context;
//
//import doyenm.zooshell.model.Paddock;
//import doyenm.zooshell.testUtils.TestUtils;
//import java.util.Arrays;
//import java.util.List;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.mockito.Mockito;
//
///**
// *
// * @author doyenm
// */
//public class PaddockCreationContextBuildTest {
//
//    private Paddock givenPaddock() {
//        return Mockito.mock(Paddock.class);
//    }
//
//    public PaddockCreationContext givenPaddockCreationContextWithCoordinatesNameAndPaddocksList(
//            int x, int y, int width, int height, String name, List<Paddock> pads) {
//        PaddockCreationContext context = Mockito.mock(PaddockCreationContext.class);
//        Mockito.when(context.getConvertedHeight()).thenReturn(height);
//        Mockito.when(context.getConvertedWidth()).thenReturn(width);
//        Mockito.when(context.getConvertedX()).thenReturn(x);
//        Mockito.when(context.getConvertedY()).thenReturn(y);
//        Mockito.when(context.getName()).thenReturn(name);
//        Mockito.when(context.get()).thenReturn(height);
//
//        return context;
//    }
//
//    @Test
//    public void shouldAddAPaddockToTheZoo() {
//        // Given
//        String name = RandomStringUtils.randomAlphabetic(10);
//        int x = TestUtils.generateInteger();
//        int y = TestUtils.generateInteger();
//        int width = TestUtils.generateInteger();
//        int height = TestUtils.generateInteger();
//
//        Paddock otherPaddock = givenPaddock();
//        PaddockCreationContext context = givenPaddockCreationContextWithCoordinatesNameAndPaddocksList(
//                x, y, width, height, name, Arrays.asList(otherPaddock));
//        // When
//        context.build();
//        // Then
//        Assertions.assertThat(context.getZoo().getPaddocks()).isNotNull();
//        Assertions.assertThat(context.getZoo().getPaddocks().size()).isEqualTo(2);
//        Assertions.assertThat(context.getZoo().getPaddocks().get(0)).isEqualTo(otherPaddock);
//        Assertions.assertThat(context.getZoo().getPaddocks().size()).isEqualTo(paddock);
//    }
//}
