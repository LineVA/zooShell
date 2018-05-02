package doyenm.zooshell.paddock.types;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockTypeContext;
import doyenm.zooshell.common.function.FindingPaddockTypeFunction;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class UpdatePaddockTypeValidatorTestTest {

    private FindingPaddockTypeFunction givenFindingPaddockType(PaddockType type) {
        FindingPaddockTypeFunction finding = mock(FindingPaddockTypeFunction.class);
        doAnswer(new Answer<FindingPaddockTypeContext>() {
            @Override
            public FindingPaddockTypeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingPaddockTypeContext) args[0];
            }
        })
                .when(finding).apply(Mockito.any(FindingPaddockTypeContext.class));
        return finding;
    }

    private UpdatePaddockTypeValidator subject;

    private FindPaddock findPaddock;
    private FindingPaddockTypeFunction findingPaddockType;

    private Paddock paddock;
    private UpdatePaddockTypeContext context;

    @Before
    public void setUp() {
        paddock = mock(Paddock.class);

        context = mock(UpdatePaddockTypeContext.class);
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        findPaddock = mock(FindPaddock.class);
        findingPaddockType = mock(FindingPaddockTypeFunction.class);
        doAnswer(new Answer<FindingPaddockTypeContext>() {
            @Override
            public FindingPaddockTypeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingPaddockTypeContext) args[0];
            }
        })
                .when(findingPaddockType).apply(Mockito.any(FindingPaddockTypeContext.class));

        subject = new UpdatePaddockTypeValidator(findPaddock, findingPaddockType);
    }


    @Test
    public void shouldReturnTrueIfThePaddockAndTheTypeExist() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockType.apply(any())).thenReturn(TestUtils.getPaddockType());
        // When
        UpdatePaddockTypeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotThePaddockType() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockType.apply(any())).thenReturn(null);
        // When
        UpdatePaddockTypeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseIfThePaddockTypeExistsButNotThePaddock() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockType.apply(any())).thenReturn(TestUtils.getPaddockType());
        // When
        UpdatePaddockTypeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseIfThePaddockTypeExistsNorThePaddockExist() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingPaddockType.apply(any())).thenReturn(null);
        // When
        UpdatePaddockTypeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(2);
    }
}
