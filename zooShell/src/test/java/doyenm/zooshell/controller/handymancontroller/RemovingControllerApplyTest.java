package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.model.Handyman;
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
public class RemovingControllerApplyTest {

    private Zoo givenZooWithNames(String name1, String name2) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Handyman> map = new HashMap<>();
        map.put(name1.toUpperCase(), Mockito.mock(Handyman.class));
        map.put(name2.toUpperCase(), Mockito.mock(Handyman.class));
        Mockito.doCallRealMethod().when(zoo).setHandymen(Mockito.anyMap());
        zoo.setHandymen(map);
        Mockito.when(zoo.getHandymen()).thenCallRealMethod();
        return zoo;
    }

    private HandymanContext givenContextWithZooAndName(Zoo zoo, String name) {
        HandymanContext context = Mockito.mock(HandymanContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getHandymanName()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldRemoveTheHandymanOfTheMap() {
        // Given
        String name1 = RandomStringUtils.randomAlphabetic(10);
        String name2 = RandomStringUtils.randomAlphabetic(10);
        Zoo zoo = givenZooWithNames(name1, name2);
        HandymanContext context = givenContextWithZooAndName(zoo, name1);
        RemovingController controller = new RemovingController();
        // When
        HandymanContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(name1).isNotEqualToIgnoringCase(name2);
        Assertions.assertThat(actualContext.getZoo().getHandymen()).hasSize(1);
        Assertions.assertThat(actualContext.getZoo().getHandymen().containsKey(name1)).isFalse();
    }
}
