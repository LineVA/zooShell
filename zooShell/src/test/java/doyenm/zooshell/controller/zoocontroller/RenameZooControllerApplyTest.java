package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.zoo.rename.RenameZooController;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class RenameZooControllerApplyTest {

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(zoo.getName()).thenCallRealMethod();
        Mockito.doCallRealMethod().when(zoo).setName(Mockito.anyString());
        return zoo;
    }

    private ZooContext givenContext(String name) {
        ZooContext context = Mockito.mock(ZooContext.class);
        Zoo zoo = givenZoo();
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getSaveName()).thenReturn(name);
        return context;
    }

    @Test
    public void shouldRenameTheZoo() {
        // Given
        String newName = RandomStringUtils.randomAlphabetic(10);
        ZooContext context = givenContext(newName);
        RenameZooController controller = new RenameZooController();
        // When
        ZooContext actualContext = controller.apply(context);
        // Then
        Assertions.assertThat(actualContext.getZoo().getName()).isEqualTo(newName);
    }
}
