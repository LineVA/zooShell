package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Paddock;
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
public class PaddockRemoveControllerApplyTest {

    private Zoo givenZooWithNames(String... names) {
        Zoo zoo = Mockito.mock(Zoo.class);
        Map<String, Paddock> map = new HashMap<>();
        for(int i = 0 ; i<names.length ; i++) {
            map.put(names[i].toUpperCase(), Mockito.mock(Paddock.class));
        }
        Mockito.doCallRealMethod().when(zoo).setPaddocks(Mockito.anyMap());
        zoo.setPaddocks(map);
        Mockito.when(zoo.getPaddocks()).thenCallRealMethod();
        return zoo;
    }

    private PaddockContext givenContextWithZooAndName(Zoo zoo, String name) {
        PaddockContext context = Mockito.mock(PaddockContext.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getPaddock()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldRemoveThePaddockOfTheMap() {
        // Given
        String name1 = RandomStringUtils.randomAlphabetic(10);
        String name2 = RandomStringUtils.randomAlphabetic(10);
        Zoo zoo = givenZooWithNames(name1, name2);
        PaddockContext context = givenContextWithZooAndName(zoo, name1);
        PaddockRemoveController controller = new PaddockRemoveController();
        // When
        PaddockContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(name1).isNotEqualToIgnoringCase(name2);
        Assertions.assertThat(actualContext.getZoo().getPaddocks().size()).isEqualTo(1);
        Assertions.assertThat(actualContext.getZoo().getPaddocks().containsKey(name1)).isFalse();
    }
}
