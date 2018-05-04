package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
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

        subject = new UpdatePaddockArrangementContext(mock(Zoo.class),
                RandomStringUtils.randomAlphabetic(5),
                Arrays.asList(),
                RandomUtils.nextBoolean());
        subject.setConvertedPaddock(paddock);
    }

    @Test
    public void shouldReturnSetWithTwoElementsWhenOneIsCommonBetweenOldAndNewFacilities(){
        // Given
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
    public void shouldReturnSetWithOnlyTheOldElementsWhenTheNewOneIsNONE(){
        // Given
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
}
