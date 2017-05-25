//package doyenm.zooshell.validator;
//
//import doyenm.zooshell.context.ZooCreationContext;
//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import org.mockito.Mockito;
//
///**
// *
// * @author doyenm
// */
//public class ZooCreationValidatorTestTest {
//
//    private ZooCreationContext givenZooCreationWithNameDimensionsSpeedAndHorizon(
//            String name, String width, String height, String speed, String horizon) {
//        ZooCreationContext context = Mockito.mock(ZooCreationContext.class);
//        Mockito.when(context.getName()).thenReturn(name);
//        Mockito.when(context.getInitHeight()).thenReturn(height);
//        Mockito.when(context.getInitWidth()).thenReturn(width);
//        Mockito.when(context.getInitSpeed()).thenReturn(speed);
//        Mockito.when(context.getInitHorizon()).thenReturn(horizon);
//        Mockito.when(context.getConvertedHeight()).thenCallRealMethod();
//        Mockito.when(context.getConvertedWidth()).thenCallRealMethod();
//        Mockito.when(context.getConvertedSpeed()).thenCallRealMethod();
//        Mockito.when(context.getConvertedHorizon()).thenCallRealMethod();
//        Mockito.doCallRealMethod().when(context.convert());
//        return context;
//    }
//
//    @Test
//    public void shouldReturnTrueWhenAllTheValuesMatchTheExpectations() {
//        // Given
//        ZooCreationValidator validator = new ZooCreationValidator();
//        String name = "12345";
//        String width = "25";
//        String height = "25";
//        String speed = "3";
//        String horizon = "5";
//        ZooCreationContext context = givenZooCreationWithNameDimensionsSpeedAndHorizon(name, width, height, speed, horizon);
//        // When
//        boolean result = validator.test(context);
//        // Then
//        Assertions.assertThat(result).isTrue();
//    }
//}
