package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.*;

public class UpdatePaddockFacilityControllerApplyTest {

    private UpdatePaddockFacilityController subject;

    private UpdatePaddockFacilityContext context;

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
        when(paddock.getFacilities()).thenCallRealMethod();
        doCallRealMethod().when(paddock).setFacilities(any());
        paddock.setFacilities(new ArrayList<>());

        zoo.getPaddocks().put(name, paddock);
        context = mock(UpdatePaddockFacilityContext.class);
        when(context.getZoo()).thenReturn(zoo);
        when(context.getConvertedPaddock()).thenReturn(paddock);

        subject = new UpdatePaddockFacilityController();
    }

    @Test
    public void shouldReturnAZooWithAPaddockWithTwoMoreFacility() {
        // Given
        PaddockFacility facility1 = TestUtils.getPaddockFacility();
        PaddockFacility facility2 = TestUtils.getPaddockFacility();
        PaddockFacility facility3 = TestUtils.getPaddockFacility();
        assertThat(facility1).isNotEqualTo(facility2);
        assertThat(facility1).isNotEqualTo(facility3);
        assertThat(facility2).isNotEqualTo(facility3);

        Set<PaddockFacility> facilitiesSet = new HashSet<>();
        facilitiesSet.add(facility1);
        facilitiesSet.add(facility2);
        facilitiesSet.add(facility3);
        when(context.difflists()).thenReturn(facilitiesSet);
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getZoo()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).isNotNull();
        assertThat(result.getZoo().getPaddocks()).hasSize(1);
        Paddock updatedPaddock = result.getZoo().getPaddocks().get(name);
        assertThat(updatedPaddock).isNotNull();
        assertThat(updatedPaddock.getFacilities()).isNotNull();
        assertThat(updatedPaddock.getFacilities()).hasSize(3);
        assertThat(updatedPaddock.getFacilities()).contains(facility1);
        assertThat(updatedPaddock.getFacilities()).contains(facility2);
        assertThat(updatedPaddock.getFacilities()).contains(facility3);
    }
}
