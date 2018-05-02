package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class UpdatePaddockArrangementControllerApplyTest {

    private UpdatePaddockArrangementController subject;

    private UpdatePaddockArrangementContext context;

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
        when(paddock.getArrangements()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setArrangements(any());
        paddock.setArrangements(new ArrayList<>());

        zoo.getPaddocks().put(name, paddock);
        context = mock(UpdatePaddockArrangementContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new UpdatePaddockArrangementController();
    }

    @Test
    public void shouldReturnAZooWithAPaddockWithOneMoreFacility() {
        // Given
        PaddockArrangement facility1 = TestUtils.getPaddockArrangement();
        PaddockArrangement facility2 = TestUtils.getPaddockArrangement();
        assertThat(facility1).isNotEqualTo(facility2);
        paddock.getArrangements().add(facility1);
        when(context.getConvertedArrangement()).thenReturn(facility2);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        Paddock updatedPaddock = result.getZoo().getPaddocks().get(name);
        assertThat(updatedPaddock).isNotNull();
        assertThat(updatedPaddock.getArrangements()).isNotNull();
        assertThat(updatedPaddock.getArrangements()).hasSize(2);
        assertThat(updatedPaddock.getArrangements()).contains(facility1);
        assertThat(updatedPaddock.getArrangements()).contains(facility2);
    }
}
