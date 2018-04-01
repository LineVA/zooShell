package doyenm.zooshell.evaluation.keeper;

import doyenm.zooshell.keeper.creation.KeeperCreationContext;
import doyenm.zooshell.keeper.creation.KeeperCreationController;
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
public class KeeperCreationControllerApplyTest {

    private AnimalKeeper givenKeeperWithName(String name) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        Mockito.when(keeper.getName()).thenReturn(name);
        return keeper;
    }

    private Map<String, AnimalKeeper> givenMapWithKeeper(AnimalKeeper keeper) {
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(keeper.getName().toUpperCase(), keeper);
        return map;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.doCallRealMethod().when(zoo).setKeepers(Mockito.anyMap());
        zoo.setKeepers(new HashMap<>());
        Mockito.when(zoo.getKeepers()).thenCallRealMethod();
        return zoo;
    }

    private KeeperCreationContext givenContextWithZooAndKeeper(Zoo zoo, String keeper) {
        KeeperCreationContext context = Mockito.mock(KeeperCreationContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getKeeper()).thenReturn(keeper);
        return context;
    }

    @Test
    public void shouldAddAKeeperWithTheGivenName() {
        // Given
        String name = RandomStringUtils.randomAlphabetic(10);
        Zoo zoo = givenZoo();
        KeeperCreationContext context = givenContextWithZooAndKeeper(zoo, name);
        KeeperCreationController controller = new KeeperCreationController();
        // When
        KeeperCreationContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getKeepers()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getKeepers().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getKeepers().get(name.toUpperCase()).getName()).isEqualTo(name);

    }
}