package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class KeeperResetOccupationsControllerApplyTest {

    private AnimalKeeper givenKeeperWithName(String name) {
        AnimalKeeper keeper = Mockito.mock(AnimalKeeper.class);
        TimedOccupation occ = TimedOccupation.builder()
                .build();
        Mockito.when(keeper.getName()).thenReturn(name);
        Mockito.doCallRealMethod().when(keeper).setOccupations(Mockito.anyList());
        Mockito.when(keeper.getOccupations()).thenCallRealMethod();
        return keeper;
    }

    private Zoo givenZooWithKeepers(Map<String, AnimalKeeper> map) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getKeepers()).thenReturn(map);
        return zoo;
    }

    private KeeperContext givenContextWithZooAndKeeper(Zoo zoo, AnimalKeeper keeper) {
        KeeperContext cxt = Mockito.mock(KeeperContext.class);
        Mockito.when(cxt.getConvertedKeeper()).thenReturn(keeper);
        Mockito.when(cxt.getZoo()).thenReturn(zoo);
        return cxt;
    }

    @Test
    public void shouldResetTheOccupations() {
        // Given
        String name = TestUtils.generateString();
        AnimalKeeper keeper = givenKeeperWithName(name);
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(name, keeper);
        Zoo zoo = givenZooWithKeepers(map);
        KeeperContext context = givenContextWithZooAndKeeper(zoo, keeper);
        KeeperResetOccupationsController controller = new KeeperResetOccupationsController();
        // When
        KeeperContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getKeepers().get(name).getOccupations()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getKeepers().get(name).getOccupations()).isEmpty();
    }
}
