package doyenm.zooshell.paddock.types;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
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

public class UpdatePaddockTypeControllerApplyTest {

    private UpdatePaddockTypeController subject;

    private UpdatePaddockTypeContext context;

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
        when(paddock.getType()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setType(any());
        paddock.setType(TestUtils.getPaddockType());

        zoo.getPaddocks().put(name, paddock);
        context = mock(UpdatePaddockTypeContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new UpdatePaddockTypeController();
    }

    @Test
    public void shouldReturnAZooWithAPaddockWithNewType() {
        // Given
        PaddockType type = TestUtils.getPaddockTypeExcluding(paddock.getType());
        when(context.getConvertedType()).thenReturn(type);
        // When
        UpdatePaddockTypeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        Paddock updatedPaddock = result.getZoo().getPaddocks().get(name);
        assertThat(updatedPaddock).isNotNull();
        assertThat(updatedPaddock.getType()).isNotNull();
        assertThat(updatedPaddock.getType()).isEqualTo(type);
    }
}
