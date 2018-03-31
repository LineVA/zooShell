package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.handyman.create.CreationController;
import doyenm.zooshell.handyman.create.HandymanCreationContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class CreationControllerApplyTest {

    private HandymanCreationContext givenContext(Zoo zoo, String name) {
        HandymanCreationContext mock = mock(HandymanCreationContext.class);
        when(mock.getName()).thenReturn(name);
        when(mock.getZoo()).thenReturn(zoo);
        when(mock.getHandymenSet()).thenReturn(new HashSet<>());
        return mock;
    }

    private Zoo givenZoo() {
        Zoo mock = mock(Zoo.class);
        Map<String, Handyman> map = new HashMap<>();
        map.put(RandomStringUtils.randomAlphanumeric(10), mock(Handyman.class));
        when(mock.getHandymen()).thenReturn(map);
        return mock;
    }

    @Test
    public void shouldReturnAContextWithTwoHandymen() {
        // GIven
        String name = RandomStringUtils.randomAlphanumeric(10);
        Zoo zoo = givenZoo();
        HandymanCreationContext context = givenContext(zoo, name);
        CreationController subject = new CreationController();
        // When
        HandymanCreationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getZoo().getHandymen()).hasSize(2);
        Assertions.assertThat(result.getZoo().getHandymen().containsKey(name.toUpperCase())).isTrue();
        Assertions.assertThat(result.getZoo().getHandymen().get(name.toUpperCase()).getName()).isEqualToIgnoringCase(name);
        Assertions.assertThat(result.getZoo().getHandymen().get(name.toUpperCase()).getAge()).isEqualTo(0);
    }
}
