package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockState;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.function.Function;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class ObsolescenceEvaluationControllerApplyTest {

    ObsolescenceEvaluationController subject;

    private Function<LightZooDto, Double> givenFunction(double added) {
        Function<LightZooDto, Double> function = mock(Function.class);
        when(function.apply(any(LightZooDto.class))).thenReturn(added);
        return function;
    }

    private Function<Double, PaddockState> givenConversionFunction() {
        Function<Double, PaddockState> function = mock(Function.class);
        return function;
    }

    private PaddockEvaluationContext givenContext(double init) {
        PaddockEvaluationContext mock = mock(PaddockEvaluationContext.class);
        Zoo zoo = mock(Zoo.class);
        when(zoo.getAnimals()).thenReturn(new HashMap<>());
        when(zoo.getMonthsPerEvaluation()).thenReturn(RandomUtils.nextInt());
        when(mock.getZoo()).thenReturn(zoo);
        Paddock pad = mock(Paddock.class);
        when(pad.getObsolescence()).thenCallRealMethod();
        doCallRealMethod().when(pad).setObsolescence(anyDouble());
        pad.setObsolescence(init);
        when(mock.getPaddock()).thenReturn(pad);
        return mock;
    }

    @Test
    public void shouldSetTheObsolescenceToTheExpectedValue() {
        // Given
        double init = 0.3;
        double added = 0.51;
        double erased = 0.2;
        Function<LightZooDto, Double> addedFunction = givenFunction(added);
        Function<LightZooDto, Double> erasedFunction = givenFunction(erased);
        PaddockEvaluationContext context = givenContext(init);
        subject = new ObsolescenceEvaluationController(addedFunction, erasedFunction, givenConversionFunction());
        // When
        PaddockEvaluationContext actual = subject.apply(context);
        // Then
        Assertions.assertThat(actual.getPaddock().getObsolescence()).isEqualTo(init - erased + added);
    }

    @Test
    public void shouldSetTheObsolescenceNotGreaterThan1() {
        // Given
        double init = 0.60;
        double added = 0.51;
        Function<LightZooDto, Double> addedFunction = givenFunction(added);
        Function<LightZooDto, Double> erasedFunction = givenFunction(0);
        PaddockEvaluationContext context = givenContext(init);
        subject = new ObsolescenceEvaluationController(addedFunction, erasedFunction, givenConversionFunction());
        // When
        PaddockEvaluationContext actual = subject.apply(context);
        // Then
        Assertions.assertThat(actual.getPaddock().getObsolescence()).isEqualTo(1.0);
    }
    
     @Test
    public void shouldSetTheObsolescenceNotLowerThan0() {
        // Given
        double init = 0.0;
        double added = 0.0;
        double erased = 0.1;
        Function<LightZooDto, Double> addedFunction = givenFunction(added);
        Function<LightZooDto, Double> erasedFunction = givenFunction(erased);
        PaddockEvaluationContext context = givenContext(init);
        subject = new ObsolescenceEvaluationController(addedFunction, erasedFunction, givenConversionFunction());
        // When
        PaddockEvaluationContext actual = subject.apply(context);
        // Then
        Assertions.assertThat(actual.getPaddock().getObsolescence()).isEqualTo(0.0);
    }

}
