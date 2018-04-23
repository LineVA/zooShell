package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;
import doyenm.zooshell.paddock.entry.PaddockEntryCreationValidator;
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
public class PaddockExtensionCreationValidatorTest {

    private PaddockExtensionCreationValidator subject;

    private PaddockExtensionCreationContext context;
    private FindPaddock findPaddock;
    private Paddock paddock;
    private Position entry;

    @Before
    public void setUp(){
        context = mock(PaddockExtensionCreationContext.class);
        doNothing().when(context).convert();
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getPaddock()).thenReturn(RandomStringUtils.randomAlphabetic(5));

        when(context.getConvertedPaddock()).thenCallRealMethod();
        doCallRealMethod().when(context).setConvertedPaddock(any());

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        paddock = mock(Paddock.class);
        entry = mock(Position.class);
        when(paddock.getEntry()).thenReturn(entry);

        findPaddock = mock(FindPaddock.class);
        subject = new PaddockExtensionCreationValidator(findPaddock);
    }

    @Test
    public void shouldReturnNoErrorWhenPaddockIsKnownAndHasEntry(){
        // Given
        when(paddock.getEntry()).thenReturn(entry);
        when(findPaddock.find(any(), anyString())).thenReturn(paddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
        assertThat(result.getConvertedPaddock()).isNotNull();
    }

    @Test
    public void shouldReturnOneErrorWhenPaddockIsUnknown(){
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(null);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getConvertedPaddock()).isNull();
    }

    @Test
    public void shouldReturnOneErrorWhenPaddockIsKnownButHasNoEntry(){
        // Given
        when(paddock.getEntry()).thenReturn(null);
        when(findPaddock.find(any(), anyString())).thenReturn(paddock);
        // When
        PaddockExtensionCreationContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
        assertThat(result.getConvertedPaddock()).isNotNull();
    }
}
