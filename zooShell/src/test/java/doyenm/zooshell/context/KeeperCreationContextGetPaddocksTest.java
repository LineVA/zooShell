/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doyenm.zooshell.context;

import doyenm.zooshell.keeper.creation.KeeperCreationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author doyenm
 */
public class KeeperCreationContextGetPaddocksTest {
    private Zoo givenZoo(String pad1, String pad2) {
        Zoo zoo = mock(Zoo.class);
        Map<String, Paddock> map = new HashMap<>();
        map.put(pad1, mock(Paddock.class));
        map.put(pad2, mock(Paddock.class));
        when(zoo.getPaddocks()).thenReturn(map);
        return zoo;
    }

    @Test
    public void shouldReturnThePaddocksOfTheZoo() {
        // Given
        String pad1 = RandomStringUtils.randomAlphanumeric(10);
        String pad2 = RandomStringUtils.randomAlphanumeric(10);
        KeeperCreationContext subject = new KeeperCreationContext(givenZoo(pad1, pad2),
                RandomStringUtils.random(10));
        // When
        Map<String, Paddock> actualPaddocks = subject.getPaddocks();
        // Then
        Assertions.assertThat(actualPaddocks).hasSize(2);
        Assertions.assertThat(actualPaddocks).containsKeys(pad1, pad2);
    }  
}
