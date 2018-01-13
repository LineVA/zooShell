package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockEvacuationControllerApplyTest {

    PaddockEvacuationController subject = new PaddockEvacuationController();

    @Test
    public void shouldHaveNoMoreReferenceToThePaddocksWhenThePaddockIsUnusableSinceThreeTurns() {
        // Given
        Paddock pad = givenPadWithTurns(3);
        Animal animal = givenAnimalWithPad(pad);
        Map<String, Animal> animals = new HashMap<>();
        animals.put(animal.getName().toUpperCase(), animal);

        AnimalKeeper keeper = givenKeeperWithPad(pad);
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(keeper.getName().toUpperCase(), keeper);

        PaddockEvaluationContext context = givenContext(pad, animals, keepers);
        // When
        PaddockEvaluationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getAnimals()).isEmpty();
        Assertions.assertThat(result.getKeepers()).isNotEmpty();
        AnimalKeeper resultKeeper = result.getKeepers().get(keeper.getName().toUpperCase());
        Assertions.assertThat(resultKeeper.getOccupations()).isEmpty();
    }
    
     @Test
    public void shouldHaveNoMoreReferenceToThePaddocksWhenThePaddockIsUnusableSinceMoreThanThreeTurns() {
        // Given
        Paddock pad = givenPadWithTurns(4);
        Animal animal = givenAnimalWithPad(pad);
        Map<String, Animal> animals = new HashMap<>();
        animals.put(animal.getName().toUpperCase(), animal);

        AnimalKeeper keeper = givenKeeperWithPad(pad);
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(keeper.getName().toUpperCase(), keeper);

        PaddockEvaluationContext context = givenContext(pad, animals, keepers);
        // When
        PaddockEvaluationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getAnimals()).isEmpty();
        Assertions.assertThat(result.getKeepers()).isNotEmpty();
        AnimalKeeper resultKeeper = result.getKeepers().get(keeper.getName().toUpperCase());
        Assertions.assertThat(resultKeeper.getOccupations()).isEmpty();
    }
    
     @Test
    public void shouldHaveTheSameReferencesToThePadodckWhenThePaddockIsUnusableSinceLessThanThreeTurns() {
        // Given
        Paddock pad = givenPadWithTurns(2);
        Animal animal = givenAnimalWithPad(pad);
        Map<String, Animal> animals = new HashMap<>();
        animals.put(animal.getName().toUpperCase(), animal);

        AnimalKeeper keeper = givenKeeperWithPad(pad);
        Map<String, AnimalKeeper> keepers = new HashMap<>();
        keepers.put(keeper.getName().toUpperCase(), keeper);

        PaddockEvaluationContext context = givenContext(pad, animals, keepers);
        // When
        PaddockEvaluationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getAnimals()).hasSize(1);
        Assertions.assertThat(result.getKeepers()).isNotEmpty();
        AnimalKeeper resultKeeper = result.getKeepers().get(keeper.getName().toUpperCase());
        Assertions.assertThat(resultKeeper.getOccupations()).hasSize(1);
    }

    private Paddock givenPadWithTurns(int turns) {
        Paddock mock = mock(Paddock.class);
        when(mock.getTurnsOfUnusableState()).thenReturn(turns);
        return mock;
    }

    private Animal givenAnimalWithPad(Paddock pad) {
        Animal mock = mock(Animal.class);
        when(mock.getPaddock()).thenReturn(pad);
        when(mock.getName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        return mock;
    }

    private AnimalKeeper givenKeeperWithPad(Paddock pad) {
        AnimalKeeper mock = mock(AnimalKeeper.class);
        TimedOccupation occ = mock(TimedOccupation.class);
        when(occ.getPaddock()).thenReturn(pad);
        List<TimedOccupation> list = new ArrayList<>();
        list.add(occ);
        when(mock.getOccupations()).thenReturn(list);
        when(mock.getName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        return mock;
    }

    private PaddockEvaluationContext givenContext(Paddock pad, Map<String, Animal> animals, Map<String, AnimalKeeper> keepers) {
        PaddockEvaluationContext mock = mock(PaddockEvaluationContext.class);
        when(mock.getPaddock()).thenReturn(pad);
        when(mock.getAnimals()).thenReturn(animals);
        when(mock.getKeepers()).thenReturn(keepers);
        return mock;
    }

}
