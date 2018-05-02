package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class ResetPaddockFacilitiesControllerApplyTest {

    private ResetPaddockFacilitiesController subject;

    private PaddockContext context;

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
        paddock.setArrangements(Lists.newArrayList(TestUtils.getPaddockArrangement()));

        zoo.getPaddocks().put(name, paddock);
        context = mock(PaddockContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new ResetPaddockFacilitiesController();
    }

    @Test
    public void shouldReturnAZooWithAPaddockWithOnlyNoneFacility() {
        // Given
        PaddockArrangement oldFacility = TestUtils.getPaddockArrangement();
        paddock.getArrangements().add(oldFacility);
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        Paddock updatedPaddock = result.getZoo().getPaddocks().get(name);
        assertThat(updatedPaddock).isNotNull();
        assertThat(updatedPaddock.getArrangements()).isNotNull();
        assertThat(updatedPaddock.getArrangements()).hasSize(1);
        assertThat(updatedPaddock.getArrangements()).contains(PaddockArrangement.NONE);
    }
}
