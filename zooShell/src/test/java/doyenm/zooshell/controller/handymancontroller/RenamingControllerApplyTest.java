package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class RenamingControllerApplyTest {

    private Handyman givenHandymanWithName(String name) {
        Handyman handyman = Mockito.mock(Handyman.class);
        Mockito.doCallRealMethod().when(handyman).setName(Mockito.anyString());
        Mockito.when(handyman.getName()).thenReturn(name);
        return handyman;
    }
    
    private Map<String, Handyman> givenMapWithHandyman(Handyman handyman) {
        Map<String, Handyman> map = new HashMap<>();
        map.put(handyman.getName(), handyman);
        return map;
    }

    private Zoo givenZooWithHandymen(Map<String, Handyman> handymen) {
        Zoo zoo = Mockito.mock(Zoo.class);
        doCallRealMethod().when(zoo).setHandymen(anyMap());
        when(zoo.getHandymen()).thenCallRealMethod();
        zoo.setHandymen(handymen);
        return zoo;
    }

    private HandymanRenameContext givenContextWithZooAndHandymen(Zoo zoo, Handyman handyman, String currentName, String newName) {
        HandymanRenameContext context = Mockito.mock(HandymanRenameContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getHandyman()).thenReturn(handyman);
        when(context.getNewName()).thenReturn(newName);
        when(context.getCurrentName()).thenReturn(currentName);
        return context;
    }

    @Ignore
    @Test
    public void shouldReplaceTheNameOfTheHandymanByTheOneSpecified() {
        // Given
        String currentName = RandomStringUtils.randomAlphabetic(10);
        Handyman handyman = givenHandymanWithName(currentName);
        String newName = RandomStringUtils.randomAlphabetic(10);
        Map<String, Handyman> handymen = givenMapWithHandyman(handyman);
        Zoo zoo = givenZooWithHandymen(handymen);
        HandymanRenameContext context = givenContextWithZooAndHandymen(zoo, handyman, currentName, newName);
        RenamingController controller = new RenamingController();
        // When
       HandymanRenameContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getHandymen()).isNotNull();
        Assertions.assertThat(actualContext.getZoo().getHandymen()).hasSize(1);
        Assertions.assertThat(actualContext.getZoo().getHandymen().get(newName.toUpperCase())).isNotNull();

    }
}
