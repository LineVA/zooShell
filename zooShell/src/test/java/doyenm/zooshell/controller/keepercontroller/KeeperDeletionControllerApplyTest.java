package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

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
        String name1 = TestUtils.generateString();
        String name2 = TestUtils.generateString();
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
