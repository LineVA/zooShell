/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.context;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperCreationContextGetKeepersTest {
    private Zoo givenZoo(String keeper1, String keeper2) {
        Zoo zoo = mock(Zoo.class);
        Map<String, AnimalKeeper> map = new HashMap<>();
        map.put(keeper1, mock(AnimalKeeper.class));
        map.put(keeper2, mock(AnimalKeeper.class));
        when(zoo.getKeepers()).thenReturn(map);
        return zoo;
    }

    @Test
    public void shouldReturnTheKeepersOfTheZoo() {
        // Given
        String keeper1 = RandomStringUtils.randomAlphanumeric(10);
        String keeper2 = RandomStringUtils.randomAlphanumeric(10);
        KeeperCreationContext subject = new KeeperCreationContext(givenZoo(keeper1, keeper2),
                RandomStringUtils.random(10));
        // When
        Map<String, AnimalKeeper> actualKeepers = subject.getKeepers();
        // Then
        Assertions.assertThat(actualKeepers).hasSize(2);
        Assertions.assertThat(actualKeepers).containsKeys(keeper1, keeper2);
    }  
}
