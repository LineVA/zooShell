package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockFacilityContext;
import doyenm.zooshell.common.function.FindingPaddockFacilityFunction;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockFacilityExistenceValidatorApplyTest {

    private UpdatePaddocFacilityExistenceValidator subject;

    private FindPaddock findPaddock;
    private FindingPaddockFacilityFunction findingPaddockFacilityFunction;
    private UpdatePaddockFacilityContext context;

    private Paddock paddock;
    private Zoo zoo;

    @Before
    public void setUp(){
        findPaddock = mock(FindPaddock.class);
        findingPaddockFacilityFunction = mock(FindingPaddockFacilityFunction.class);
        doAnswer(new Answer<FindingPaddockFacilityContext>() {
            @Override
            public FindingPaddockFacilityContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingPaddockFacilityContext) args[0];
            }
        })
                .when(findingPaddockFacilityFunction).apply(any(FindingPaddockFacilityContext.class));

        paddock = mock(Paddock.class);
        zoo = mock(Zoo.class);

        context = mock(UpdatePaddockFacilityContext.class);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getZoo()).thenReturn(zoo);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());


        subject = new UpdatePaddocFacilityExistenceValidator(findPaddock, findingPaddockFacilityFunction);
    }

    @Test
    public void shouldReturnTrueIfThePaddockAndAllTheFacilitiesExist() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        List<String> arrangementsStr = Arrays.asList(
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5));
        when(context.getFacilities()).thenReturn(arrangementsStr);
        when(findingPaddockFacilityFunction.apply(any()))
                .thenReturn(TestUtils.getPaddockArrangement())
                .thenReturn(TestUtils.getPaddockArrangement());
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotOneOfTheFacilities() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        List<String> arrangementsStr = Arrays.asList(
                RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphabetic(5));
        when(context.getFacilities()).thenReturn(arrangementsStr);
        when(findingPaddockFacilityFunction.apply(any()))
                .thenReturn(null)
                .thenReturn(TestUtils.getPaddockArrangement());
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }
    
    @Test
    public void shouldReturnFalseIfTheFacilitiesExistButNotThePaddock() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockFacilityFunction.apply(any())).thenReturn(TestUtils.getPaddockArrangement());
        // When
        UpdatePaddockFacilityContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }
}
