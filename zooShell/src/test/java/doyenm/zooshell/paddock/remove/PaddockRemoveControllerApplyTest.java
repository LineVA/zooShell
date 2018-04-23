package doyenm.zooshell.paddock.remove;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.model.*;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.paddock.remove.PaddockRemoveController;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveControllerApplyTest {

    PaddockRemoveController subject;

    PaddockContext context;
    Zoo zoo;

    Paddock paddock;
    String paddockName;

    AnimalKeeper animalKeeper;
    TimedOccupation keeperOccupation;
    String keeperName;

    Handyman handyman;
    String handymanName;

    @Before
    public void setUp(){
        zoo = mock(Zoo.class);

        paddock = mock(Paddock.class);
        paddockName = RandomStringUtils.randomAlphabetic(10);

        keeperName = RandomStringUtils.randomAlphabetic(10);
        animalKeeper = mock(AnimalKeeper.class);
        keeperOccupation = mock(TimedOccupation.class);
        when(keeperOccupation.getPaddock()).thenReturn(paddock);
        when(animalKeeper.getOccupations()).thenReturn(Lists.newArrayList(keeperOccupation));
        when(animalKeeper.getName()).thenReturn(keeperName);
        when(zoo.getKeepers()).thenReturn(ImmutableMap.<String, AnimalKeeper>builder().put(keeperName.toUpperCase(), animalKeeper).build());

        handymanName = RandomStringUtils.randomAlphabetic(10);
        handyman = mock(Handyman.class);
        when(handyman.getAffectations()).thenReturn(Lists.newArrayList(paddock));
        when(handyman.getName()).thenReturn(handymanName);
        when(zoo.getHandymen()).thenReturn(ImmutableMap.<String, Handyman>builder().put(handymanName.toUpperCase(), handyman).build());

        context = mock(PaddockContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getPaddock()).thenReturn(paddockName);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new PaddockRemoveController();
    }

    @Test
    public void shouldRemoveThePaddockOfTheMapAndTheOccupationsOfTheKeepersAndTheHandymen() {
        // Given
        String name2 = RandomStringUtils.randomAlphabetic(10);
        givenZooWithNames(paddockName, name2);
        when(keeperOccupation.getPaddock()).thenReturn(paddock);
        // When
        PaddockContext actualContext = subject.apply(context);
        // Then
        assertThat(paddockName).isNotEqualToIgnoringCase(name2);
        assertThat(actualContext).isNotNull();
        assertThat(actualContext.getZoo()).isNotNull();
        assertThat(actualContext.getZoo().getPaddocks()).isNotNull();
        assertThat(actualContext.getZoo().getPaddocks()).hasSize(1);
        assertThat(actualContext.getZoo().getPaddocks().containsKey(paddockName)).isFalse();

        assertThat(actualContext.getZoo().getKeepers()).isNotNull();
        AnimalKeeper actualKeeper = actualContext.getZoo().getKeepers().get(keeperName.toUpperCase());
        assertThat(actualKeeper).isNotNull();
        assertThat(actualKeeper.getOccupations()).isNotNull();
        assertThat(actualKeeper.getOccupations()).isEmpty();

        assertThat(actualContext.getZoo().getHandymen()).isNotNull();
        Handyman actualHandyman = actualContext.getZoo().getHandymen().get(handymanName.toUpperCase());
        assertThat(actualHandyman).isNotNull();
        assertThat(actualHandyman.getAffectations()).isNotNull();
        assertThat(actualHandyman.getAffectations()).isEmpty();
    }

    private void givenZooWithNames(String... names) {
        Map<String, Paddock> map = new HashMap<>();
        for(int i = 0 ; i<names.length ; i++) {
            map.put(names[i].toUpperCase(), mock(Paddock.class));
        }
        doCallRealMethod().when(zoo).setPaddocks(Mockito.anyMap());
        zoo.setPaddocks(map);
        when(zoo.getPaddocks()).thenCallRealMethod();
    }

}
