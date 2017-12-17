package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import java.util.function.Function;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        Function<LightZooDto, Double> function = givenFunction(added);
        PaddockEvaluationContext context = givenContext(init);
        subject = new ObsolescenceEvaluationController(function);
        // When
        PaddockEvaluationContext actual = subject.apply(context);
        // Then
        Assertions.assertThat(actual.getPaddock().getObsolescence()).isEqualTo(init + added);
    }
    
     @Test
    public void shouldSetTheObsolescenceNotGreaterThan1() {
        // Given
        double init = 0.60;
        double added = 0.51;
        Function<LightZooDto, Double> function = givenFunction(added);
        PaddockEvaluationContext context = givenContext(init);
        subject = new ObsolescenceEvaluationController(function);
        // When
        PaddockEvaluationContext actual = subject.apply(context);
        // Then
        Assertions.assertThat(actual.getPaddock().getObsolescence()).isEqualTo(1.0);
    }
}
