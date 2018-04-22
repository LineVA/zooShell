package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class PaddockEntryCreationValidatorTest {

    private PaddockEntryCreationValidator subject;

    private PaddockEntryCreationContext context;
    private FindPaddock findPaddock;

    @Before
    public void setUp(){
        context = mock(PaddockEntryCreationContext.class);
        doNothing().when(context).convert();
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getPaddock()).thenReturn(RandomStringUtils.randomAlphabetic(5));

        when(context.getConvertedPaddock()).thenCallRealMethod();
        doCallRealMethod().when(context).setConvertedPaddock(any());

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        findPaddock = mock(FindPaddock.class);
        subject = new PaddockEntryCreationValidator(findPaddock);
    }

    @Test
    public void shouldReturnNoErrorWhenPaddockIsKnown(){
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(mock(Paddock.class));
        // When
        PaddockEntryCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getConvertedPaddock()).isNotNull();
    }
}
