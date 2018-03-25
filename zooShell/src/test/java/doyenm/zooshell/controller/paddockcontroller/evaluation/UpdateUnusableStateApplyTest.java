/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockState;
import org.apache.commons.lang.math.RandomUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;
import java.util.function.Function;

import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class UpdateUnusableStateApplyTest {

    UpdateUnusableState function;

    @Test
    public void shouldSetTheTurnsOfUnusableStateToZeroWhenThePaddockIsNotUnusable() {
        // Given
        Function converter = givenConverter(getState());
        function = new UpdateUnusableState(converter);
        int turns = RandomUtils.nextInt();
        Paddock pad = givenPaddock(turns);
        PaddockEvaluationContext context = givenContext(pad);
        // When
        PaddockEvaluationContext result = function.apply(context);
        // Then
        Assertions.assertThat(result.getPaddock().getTurnsOfUnusableState()).isEqualTo(0);
    }
    
     @Test
    public void shouldIncrementTheTurnsOfUnusableStateWhenThePaddockIsUnusable() {
        // Given
        Function converter = givenConverter(PaddockState.UNSUABLE);
        function = new UpdateUnusableState(converter);
        int turns = RandomUtils.nextInt();
        Paddock pad = givenPaddock(turns);
        PaddockEvaluationContext context = givenContext(pad);
        // When
        PaddockEvaluationContext result = function.apply(context);
        // Then
        Assertions.assertThat(result.getPaddock().getTurnsOfUnusableState()).isEqualTo(turns + 1);
    }
    
    private Paddock givenPaddock(int turns){
        Paddock pad = mock(Paddock.class);
        when(pad.getTurnsOfUnusableState()).thenCallRealMethod();
        doCallRealMethod().when(pad).setTurnsOfUnusableState(Mockito.anyInt());
        pad.setTurnsOfUnusableState(turns);
        return pad;
    }

    private Function givenConverter(PaddockState state){
        Function mock = mock(Function.class);
        when(mock.apply(Mockito.anyDouble())).thenReturn(state);
        return mock;
    }
    
    private PaddockEvaluationContext givenContext(Paddock pad){
        PaddockEvaluationContext context = mock(PaddockEvaluationContext.class);
        when(context.getPaddock()).thenReturn(pad);
        return context;
    }
    
    private PaddockState getState() {
        PaddockState state = PaddockState.UNSUABLE;
        Random random = new Random();
        while (PaddockState.UNSUABLE == state) {
            int i = random.nextInt(PaddockState.values().length);
            state = PaddockState.values()[i];
        }
        return state;
    }
}
