package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaddockEntryCreationControllerApplyTest {

    private PaddockEntryCreationController subject;

    private PaddockEntryCreationContext context;

    private String name;
    private int x;
    private int y;

    @Before
    public void setUp() {
        name = RandomStringUtils.randomAlphabetic(5);
        x = RandomUtils.nextInt();
        y = RandomUtils.nextInt();

        Zoo zoo = mock(Zoo.class);
        when(zoo.getPaddocks()).thenCallRealMethod();
        doCallRealMethod().when(zoo).setPaddocks(anyMap());
        zoo.setPaddocks(new HashMap<>());

        Paddock paddock = mock(Paddock.class);
        when(paddock.getName()).thenReturn(name);
        when(paddock.getEntry()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setEntry(any());
        paddock.setEntry(null);

        zoo.getPaddocks().put(name, paddock);
        context = mock(PaddockEntryCreationContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getConvertedX()).thenReturn(x);
        when(context.getConvertedY()).thenReturn(y);

        subject = new PaddockEntryCreationController();
    }

    @Test
    public void shouldReturnAZooWithOneMorePaddock() {
        // Given
        // When
        PaddockEntryCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        Paddock addedPad = result.getZoo().getPaddocks().get(name);
        assertThat(addedPad).isNotNull();
        assertThat(addedPad.getName()).isEqualTo(name);
        assertThat(addedPad.getEntry()).isNotNull();
        assertThat(addedPad.getEntry().getX()).isEqualTo(x);
        assertThat(addedPad.getEntry().getY()).isEqualTo(y);
    }
}
