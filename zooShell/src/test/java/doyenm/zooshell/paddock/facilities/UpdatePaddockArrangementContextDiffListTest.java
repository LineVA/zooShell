package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
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

public class UpdatePaddockArrangementContextDiffListTest {

    private UpdatePaddockArrangementContext subject;

    private Paddock paddock;

    @Before
    public void setUp(){
        paddock = mock(Paddock.class);
    }

    @Test
    public void shouldReturnSetWithTwoElementsWhenAddingIsSetToTrueAndOneIsCommonBetweenOldAndNewFacilities(){
        // Given
        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
               true);
        subject.setConvertedPaddock(paddock);

        PaddockArrangement facility1 = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE);
        PaddockArrangement commonFacility = TestUtils.getPaddockArrangementExcluding(facility1, PaddockArrangement.NONE);
        subject.setConvertedArrangements(Lists.newArrayList(facility1, commonFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(commonFacility));
        // When
        Set<PaddockArrangement> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(2);
        assertThat(result).contains(facility1);
        assertThat(result).contains(commonFacility);
    }

    @Test
    public void shouldReturnSetWithOnlyTheOldElementsWhenAddingIsSetToTrueAndTheNewOneIsNONE(){
        // Given
        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                true);
        subject.setConvertedPaddock(paddock);

        PaddockArrangement oldFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE);
        subject.setConvertedArrangements(Lists.newArrayList(PaddockArrangement.NONE));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockArrangement> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(oldFacility);
    }

    @Test
    public void shouldReturnSetWithOneElementsWhenAddingIsSetToFalseAndOneIsCommonBetweenPresentAndRemovedFacilities(){
        // Given
        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockArrangement facility1 = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE);
        PaddockArrangement commonFacility = TestUtils.getPaddockArrangementExcluding(facility1, PaddockArrangement.NONE);
        subject.setConvertedArrangements(Lists.newArrayList(commonFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(facility1, commonFacility));
        // When
        Set<PaddockArrangement> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(facility1);
    }

    @Test
    public void shouldReturnSetWithOnlyTheElementNoneWhenAddingIsSetToFalseAndWeAreRemovingAllOfThem(){
        // Given
        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockArrangement oldFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE);
        subject.setConvertedArrangements(Lists.newArrayList(oldFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockArrangement> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(PaddockArrangement.NONE);
    }

    @Test
    public void shouldReturnTheInitialSetWhenAddingIsSetToFalseAndWeAreRemovingFacilityWhichAreNotInThePaddock(){
        // Given
        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                false);
        subject.setConvertedPaddock(paddock);

        PaddockArrangement oldFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE);
        PaddockArrangement unknownFacility = TestUtils.getPaddockArrangementExcluding(PaddockArrangement.NONE, oldFacility);

        subject.setConvertedArrangements(Lists.newArrayList(unknownFacility));
        when(paddock.getArrangements()).thenReturn(Arrays.asList(oldFacility));
        // When
        Set<PaddockArrangement> result = subject.difflists();
        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).contains(oldFacility);
    }
}
