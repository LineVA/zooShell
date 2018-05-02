package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockArrangementContext;
import doyenm.zooshell.common.function.FindingPaddockArrangementFunction;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockArrangmentValidatorApplyTest {

    private UpdatePaddockArrangementValidator subject;

    private FindPaddock findPaddock;
    private FindingPaddockArrangementFunction findingPaddockArrangementFunction;
    private UpdatePaddockArrangementContext context;

    private Paddock paddock;
    private Zoo zoo;

    @Before
    public void setUp(){
        findPaddock = mock(FindPaddock.class);
        findingPaddockArrangementFunction = mock(FindingPaddockArrangementFunction.class);
        doAnswer(new Answer<FindingPaddockArrangementContext>() {
            @Override
            public FindingPaddockArrangementContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingPaddockArrangementContext) args[0];
            }
        })
                .when(findingPaddockArrangementFunction).apply(any(FindingPaddockArrangementContext.class));

        paddock = mock(Paddock.class);
        zoo = mock(Zoo.class);

        context = mock(UpdatePaddockArrangementContext.class);
        when(context.getConvertedPaddock()).thenReturn(paddock);
        when(context.getZoo()).thenReturn(zoo);

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());


        subject = new UpdatePaddockArrangementValidator(findPaddock, findingPaddockArrangementFunction);
    }

    @Test
    public void shouldReturnTrueIfThePaddockAndTheFacilityExist() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockArrangementFunction.apply(any())).thenReturn(TestUtils.getPaddockArrangement());
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }
    
    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotTheFacility() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockArrangementFunction.apply(any())).thenReturn(null);
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }
    
    @Test
    public void shouldReturnTrueIfTheFacilityExistsButNotThePaddock() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockArrangementFunction.apply(any())).thenReturn(TestUtils.getPaddockArrangement());
        // When
        UpdatePaddockArrangementContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }
}
