package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperRenameControllerApplyTest {

    private AnimalKeeper givenKeeperWithName(String name) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getName()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(keeper).setName(Mockito.anyString());
        keeper.setName(name);
        return keeper;
    }

    private Map<String, AnimalKeeper> givenMapWithKeeper(AnimalKeeper keeper) {
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(keeper.getName(), keeper);
        return map;
    }

    private Zoo givenZooWithKeepers(Map<String, AnimalKeeper> map) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.doCallRealMethod().when(zoo).setKeepers(Mockito.anyMap());
        zoo.setKeepers(map);
        Mockito.when(zoo.getKeepers()).thenCallRealMethod();
        return zoo;
    }

    private KeeperRenameContext givenContextWithZooNewNameAndKeeper(Zoo zoo, String newName, AnimalKeeper keeper) {
        KeeperRenameContext context = Mockito.mock(KeeperRenameContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        String name = keeper.getName();
        Mockito.when(context.getKeeper()).thenReturn(name);
        Mockito.when(context.getConvertedKeeper()).thenReturn(keeper);
        Mockito.when(context.getNewKeeperName()).thenReturn(newName);
        return context;
    }

    @Test
    public void shouldRenameTheKeeperWithTheGivenName() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        String newName = RandomStringUtils.randomAlphabetic(10);
        AnimalKeeper keeper = givenKeeperWithName(name);
        Map<String, AnimalKeeper> map = givenMapWithKeeper(keeper);
        Zoo zoo = givenZooWithKeepers(map);
        KeeperRenameContext context = givenContextWithZooNewNameAndKeeper(zoo, newName, keeper);
        KeeperRenameController controller = new KeeperRenameController();
        // When
        KeeperRenameContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getKeepers()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getKeepers().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getKeepers().get(newName).getName()).isEqualTo(newName);

    }
}
