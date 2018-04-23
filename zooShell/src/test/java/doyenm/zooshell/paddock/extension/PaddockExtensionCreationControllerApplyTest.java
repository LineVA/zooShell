package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.model.Coordinates;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationController;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class PaddockExtensionCreationControllerApplyTest {

    private PaddockExtensionCreationController subject;

    private PaddockExtensionCreationContext context;

    private String name;
    private int x;
    private int y;
    private int width;
    private int height;

    @Before
    public void setUp() {
        name = RandomStringUtils.randomAlphabetic(5);
        x = RandomUtils.nextInt();
        y = RandomUtils.nextInt();
        width = RandomUtils.nextInt();
        height = RandomUtils.nextInt();

        Zoo zoo = mock(Zoo.class);
        when(zoo.getPaddocks()).thenCallRealMethod();
        doCallRealMethod().when(zoo).setPaddocks(anyMap());
        zoo.setPaddocks(new HashMap<>());

        Paddock paddock = mock(Paddock.class);
        when(paddock.getName()).thenReturn(name);
        when(paddock.getExtensions()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setExtensions(any());
        paddock.setExtensions(new ArrayList<>());

        zoo.getPaddocks().put(name, paddock);
        context = mock(PaddockExtensionCreationContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getConvertedX()).thenReturn(x);
        when(context.getConvertedY()).thenReturn(y);
        when(context.getConvertedHeight()).thenReturn(height);
        when(context.getConvertedWidth()).thenReturn(width);

        subject = new PaddockExtensionCreationController();
    }

    @Test
    public void shouldReturnAZooWithOneMorePaddock() {
        // Given
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();

        Paddock addedPad = result.getZoo().getPaddocks().get(name);
        assertThat(addedPad).isNotNull();
        assertThat(addedPad.getName()).isEqualTo(name);
        assertThat(addedPad.getExtensions()).isNotNull();
        assertThat(addedPad.getExtensions()).hasSize(1);

        Coordinates extension = addedPad.getExtensions().get(0);
        assertThat(extension.getPosition().getX()).isEqualTo(x);
        assertThat(extension.getPosition().getY()).isEqualTo(y);
        assertThat(extension.getWidth()).isEqualTo(width);
        assertThat(extension.getHeight()).isEqualTo(height);
    }
}
