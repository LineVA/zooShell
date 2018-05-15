package doyenm.zooshell.paddock;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PaddockValidatorApplyTest {

    private PaddockValidator subject;

    private FindPaddock findPaddock;
    private PaddockContext context;

    @Before
    public void setUp() {
        findPaddock = mock(FindPaddock.class);

        context = mock(PaddockContext.class);
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        subject = new PaddockValidator(findPaddock);
    }

    @Test
    public void shouldReturnContextWithNoErrorWhenThePaddockExists() {
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(mock(Paddock.class));
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnContextWithOneErrorWhenThePaddockDoesNotExist() {
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(null);
        // When
        PaddockContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getErrors().get(0).getType()).isEqualTo(ErrorType.UNKNOWN_PADDOCK);
    }
}
