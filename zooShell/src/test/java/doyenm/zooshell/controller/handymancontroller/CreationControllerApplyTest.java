package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.HandymanCreationValidator;
import doyenm.zooshell.validator.name.NameValidator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
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
    public void shouldReturnAContextWithtwoHandymen() {
        // GIven
        String name = RandomStringUtils.randomAlphanumeric(10);
        Zoo zoo = givenZoo();
        HandymanCreationContext context = givenContext(zoo, name);
        CreationController subject = new CreationController();
        // When
        HandymanCreationContext result = subject.apply(context);
        // Then
        Assertions.assertThat(result.getZoo().getHandymen()).hasSize(2);
        Assertions.assertThat(result.getZoo().getHandymen().containsKey(name)).isTrue();
        Assertions.assertThat(result.getZoo().getHandymen().get(name).getName()).isEqualToIgnoringCase(name);
        Assertions.assertThat(result.getZoo().getHandymen().get(name).getAge()).isEqualTo(0);
    }
}
