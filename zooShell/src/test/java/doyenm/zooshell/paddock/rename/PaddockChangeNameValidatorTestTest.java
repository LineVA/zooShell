package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.rename.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.paddock.rename.PaddockChangeNameValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameValidatorTestTest {

    private PaddockChangeNameValidator subject;

    private FindPaddock findPaddock;
    private NameValidator nameValidator;
    private PaddockChangeNameContext context;


    @Before
    public void setUp(){
        findPaddock = mock(FindPaddock.class);
        nameValidator = mock(NameValidator.class);

        context = mock(PaddockChangeNameContext.class);
        when(context.getNewName()).thenReturn(RandomStringUtils.random(10));
        when(context.getCurrentName()).thenReturn(RandomStringUtils.random(10));
        when(context.getZoo()).thenReturn(mock(Zoo.class));
        when(context.getPaddocks()).thenReturn(new HashSet<>());
        doNothing().when(context).setConvertedPaddock(Mockito.any(Paddock.class));

        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(anyList());
        context.setErrors(new ArrayList<>());

        subject = new PaddockChangeNameValidator(findPaddock, nameValidator);
    }

    /**
     * An paddock can be renamed if : - the paddock has an entry - the paddock
     * exists - the new name is shorter than 50 characters
     */
    @Test
    public void shouldReturnTrueWhenThePaddockCanBeRenamed() {
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(mock(Paddock.class));
        when(nameValidator.test(any())).thenReturn(true);
        // When
        PaddockChangeNameContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(null);
        when(nameValidator.test(any())).thenReturn(true);
        // When
        PaddockChangeNameContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsNotOK() {
        // Given
        when(findPaddock.find(any(), anyString())).thenReturn(mock(Paddock.class));
        when(nameValidator.test(any())).thenReturn(false);
        // When
        PaddockChangeNameContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

}
