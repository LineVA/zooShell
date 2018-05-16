package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdatePaddockFacilityContextDiffListTest {

    private UpdatePaddockFacilityContext subject;

    private Paddock paddock;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
    }

    @Test
    public void shouldReturnSetWithTwoElementsWhenAddingIsSetToTrueAndOneIsCommonBetweenOldAndNewFacilities(){
        // Given
        subject = new UpdatePaddockFacilityContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
               true);
        subject.setConvertedPaddock(paddock);

        PaddockFacility facility1 = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        PaddockFacility commonFacility = TestUtils.getPaddockFacilityExcluding(facility1, PaddockFacility.NONE);
        subject.setConvertedFacilities(Lists.newArrayList(facility1, commonFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(commonFacility));
        // When
        Set<PaddockFacility> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(facility1);
        assertThat(result).contains(commonFacility);
    }

    @Test
    public void shouldReturnSetWithOnlyTheOldElementsWhenAddingIsSetToTrueAndTheNewOneIsNONE(){
        // Given
        subject = new UpdatePaddockFacilityContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                true);
        subject.setConvertedPaddock(paddock);

        PaddockFacility oldFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        subject.setConvertedFacilities(Lists.newArrayList(PaddockFacility.NONE));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockFacility> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(oldFacility);
    }

    @Test
    public void shouldReturnSetWithOneElementsWhenAddingIsSetToFalseAndOneIsCommonBetweenPresentAndRemovedFacilities(){
        // Given
        subject = new UpdatePaddockFacilityContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockFacility facility1 = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        PaddockFacility commonFacility = TestUtils.getPaddockFacilityExcluding(facility1, PaddockFacility.NONE);
        subject.setConvertedFacilities(Lists.newArrayList(commonFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(facility1, commonFacility));
        // When
        Set<PaddockFacility> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(facility1);
    }

    @Test
    public void shouldReturnSetWithOnlyTheElementNoneWhenAddingIsSetToFalseAndWeAreRemovingAllOfThem(){
        // Given
        subject = new UpdatePaddockFacilityContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockFacility oldFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        subject.setConvertedFacilities(Lists.newArrayList(oldFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockFacility> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(PaddockFacility.NONE);
    }

    @Test
    public void shouldReturnTheInitialSetWhenAddingIsSetToFalseAndWeAreRemovingFacilityWhichAreNotInThePaddock(){
        // Given
        subject = new UpdatePaddockFacilityContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockFacility oldFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE);
        PaddockFacility unknownFacility = TestUtils.getPaddockFacilityExcluding(PaddockFacility.NONE, oldFacility);

        subject.setConvertedFacilities(Lists.newArrayList(unknownFacility));
        when(paddock.getFacilities()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockFacility> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(oldFacility);
    }
}
