package doyenm.zooshell.validator;

import doyenm.zooshell.paddock.rename.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.paddock.rename.PaddockChangeNameValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;

import static org.mockito.Matchers.any;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameValidatorTestTest {

    private FindPaddock givenFindWithPad(Paddock pad) {
        FindPaddock find = Mockito.mock(FindPaddock.class);
        Mockito.doReturn(pad).when(find).find(Mockito.any(Zoo.class), Mockito.anyString());
        return find;
    }

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
        return mock;
    }

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        return pad;
    }

    private PaddockChangeNameContext givenContext() {
        PaddockChangeNameContext context = Mockito.mock(PaddockChangeNameContext.class);
        Mockito.when(context.getNewName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(context.getCurrentName()).thenReturn(RandomStringUtils.random(10));
        Mockito.when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        Mockito.when(context.getPaddocks()).thenReturn(new HashSet<>());
        Mockito.doNothing().when(context).setConvertedPaddock(Mockito.any(Paddock.class));
        return context;
    }

    /**
     * An paddock can be renamed if : - the paddock has an entry - the paddock
     * exists - the new name is shorter than 50 characters
     */
    @Test
    public void shouldReturnTrueWhenThePaddockCanBeRenamed() {
        // Given
        Paddock convertedPaddock = givenPaddock();
        FindPaddock findPaddock = givenFindWithPad(convertedPaddock);
        NameValidator nameValidator = givenNameTest(true);
        PaddockChangeNameContext context = givenContext();
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator(
                findPaddock,
                nameValidator
        );
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenThePaddockDoesNotExist() {
        // Given
        Paddock convertedPaddock = null;
        FindPaddock findPaddock = givenFindWithPad(convertedPaddock);
        NameValidator nameValidator = givenNameTest(true);
        PaddockChangeNameContext context = givenContext();
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator(
                findPaddock,
                nameValidator
        );
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsNotOK() {
        // Given
        Paddock convertedPaddock = givenPaddock();
        FindPaddock findPaddock = givenFindWithPad(convertedPaddock);
        NameValidator nameValidator = givenNameTest(false);
        PaddockChangeNameContext context = givenContext();
        PaddockChangeNameValidator validator = new PaddockChangeNameValidator(
                findPaddock,
                nameValidator
        );
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

}
