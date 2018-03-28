package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.keeper.KeeperContext;
import doyenm.zooshell.keeper.remove.KeeperDeletionController;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author doyenm
 */
public class KeeperDeletionControllerApplyTest {

    private Zoo givenZooWithNames(String name1, String name2) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(name1, Mockito.mock(AnimalKeeper.class));
        map.put(name2, Mockito.mock(AnimalKeeper.class));
        Mockito.doCallRealMethod().when(zoo).setKeepers(Mockito.anyMap());
        zoo.setKeepers(map);
        Mockito.when(zoo.getKeepers()).thenCallRealMethod();
        return zoo;
    }

    private KeeperContext givenContextWithZooAndName(Zoo zoo, String name) {
        KeeperContext context = Mockito.mock(KeeperContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getKeeper()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldDeleteTheKeeperOfTheMap() {
        // Given
        String name1 = RandomStringUtils.randomAlphabetic(10);
        String name2 = RandomStringUtils.randomAlphabetic(10);
        Zoo zoo = givenZooWithNames(name1, name2);
        KeeperContext context = givenContextWithZooAndName(zoo, name1);
        KeeperDeletionController controller = new KeeperDeletionController();
        // When
        KeeperContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(name1).isNotEqualToIgnoringCase(name2);
        Assertions.assertThat(actualContext.getZoo().getKeepers().size()).isEqualTo(1);
    }
}
