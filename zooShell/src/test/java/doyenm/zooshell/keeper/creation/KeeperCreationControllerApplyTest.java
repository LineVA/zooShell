package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeeperCreationControllerApplyTest {

    private KeeperCreationController subject;
    private KeeperCreationContext context;

    @Before
    public void setUp(){
        subject = new KeeperCreationController();
        context = mock(KeeperCreationContext.class);
    }


    @Test
    public void shouldReturnAContextWithTwoKeepers() {
        // Given
        String name = RandomStringUtils.randomAlphanumeric(10);
        Zoo zoo = givenZoo();
        context = givenContext(zoo, name);
        // When
        KeeperCreationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getZoo().getKeepers()).hasSize(2);
        Assertions.assertThat(result.getZoo().getKeepers().containsKey(name.toUpperCase())).isTrue();
        Assertions.assertThat(result.getZoo().getKeepers().get(name.toUpperCase()).getName()).isEqualToIgnoringCase(name);
        Assertions.assertThat(result.getZoo().getKeepers().get(name.toUpperCase()).getAge()).isEqualTo(0);
    }

    private KeeperCreationContext givenContext(Zoo zoo, String name) {
        KeeperCreationContext mock = mock(KeeperCreationContext.class);
        when(mock.getKeeper()).thenReturn(name);
        when(mock.getZoo()).thenReturn(zoo);
        when(mock.getKeepers()).thenReturn(new HashMap<>());
        return mock;
    }

    private Zoo givenZoo() {
        Zoo mock = mock(Zoo.class);
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphanumeric(10), mock(AnimalKeeper.class));
        when(mock.getKeepers()).thenReturn(map);
        return mock;
    }

}
