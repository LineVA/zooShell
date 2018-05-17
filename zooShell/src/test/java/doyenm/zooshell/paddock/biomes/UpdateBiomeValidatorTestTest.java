package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.FindingBiomeContext;
import doyenm.zooshell.paddock.FindingBiomeFunction;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.testUtils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author doyenm
 */
public class UpdateBiomeValidatorTestTest {

    UpdateBiomeValidator subject;

    private FindPaddock findPaddock;
    private FindingBiomeFunction findingBiomeFunction;

    private Paddock paddock;
    private UpdateBiomeContext context;


    @Before
    public void setUp() {
        paddock = mock(Paddock.class);

        context = mock(UpdateBiomeContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(paddock);
        Mockito.when(context.getZoo()).thenReturn(mock(Zoo.class));

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        findPaddock = mock(FindPaddock.class);
        findingBiomeFunction = mock(FindingBiomeFunction.class);
        Mockito.doAnswer(new Answer<FindingBiomeContext>() {
            @Override
            public FindingBiomeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingBiomeContext) args[0];
            }
        })
                .when(findingBiomeFunction).apply(any(FindingBiomeContext.class));

        subject = new UpdateBiomeValidator(findPaddock, findingBiomeFunction);
    }

    @Test
    public void shouldReturnTrueIfThePaddockAndTheBiomeExist() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingBiomeFunction.apply(any())).thenReturn(TestUtils.getBiome());
        // When
        UpdateBiomeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotTheBiome() {
        // Given
        doReturn(paddock).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingBiomeFunction.apply(any())).thenReturn(null);
        // When
        UpdateBiomeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseIfTheBiomeExistsButNotThePaddock() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingBiomeFunction.apply(any())).thenReturn(TestUtils.getBiome());
        // When
        UpdateBiomeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseIfTheBiomeDoesNotExistNorThePaddock() {
        // Given
        doReturn(null).when(findPaddock).find(any(Zoo.class), anyString());
        when(findingBiomeFunction.apply(any())).thenReturn(null);
        // When
        UpdateBiomeContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(2);
    }
}
