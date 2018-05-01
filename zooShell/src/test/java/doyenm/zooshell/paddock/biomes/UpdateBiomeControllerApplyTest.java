package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class UpdateBiomeControllerApplyTest {

    private UpdateBiomeController subject;

    private UpdateBiomeContext context;

    private String name;
    private Paddock paddock;

    @Before
    public void setUp() {
        name = RandomStringUtils.randomAlphabetic(5);

        Zoo zoo = mock(Zoo.class);
        when(zoo.getPaddocks()).thenCallRealMethod();
        doCallRealMethod().when(zoo).setPaddocks(anyMap());
        zoo.setPaddocks(new HashMap<>());

        paddock = mock(Paddock.class);
        when(paddock.getName()).thenReturn(name);
        when(paddock.getBiome()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setBiome(any());
        paddock.setBiome(TestUtils.getBiome());

        zoo.getPaddocks().put(name, paddock);
        context = mock(UpdateBiomeContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new UpdateBiomeController();
    }

    @Test
    public void shouldReturnAZooWithAPaddockWithNewBiome() {
        // Given
        Biome newBiome = TestUtils.getBiomeExcluding(paddock.getBiome());
        when(context.getConvertedBiome()).thenReturn(newBiome);
        // When
        UpdateBiomeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        Paddock updatedPaddock = result.getZoo().getPaddocks().get(name);
        assertThat(updatedPaddock).isNotNull();
        assertThat(updatedPaddock.getBiome()).isNotNull();
        assertThat(updatedPaddock.getBiome()).isEqualTo(newBiome);
    }
}
