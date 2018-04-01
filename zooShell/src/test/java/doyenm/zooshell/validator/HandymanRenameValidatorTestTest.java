package doyenm.zooshell.validator;

import doyenm.zooshell.common.FindHandyman;
import doyenm.zooshell.handyman.rename.HandymanRenameContext;
import doyenm.zooshell.handyman.rename.HandymanRenameValidator;
import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;

/**
 *
 * @author doyenm
 */
public class HandymanRenameValidatorTestTest {

    private HandymanRenameContext givenContext(Handyman handyman) {
        HandymanRenameContext context = Mockito.mock(HandymanRenameContext.class);
        Mockito.when(context.getCurrentName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getNewName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        Mockito.when(context.getHandyman()).thenReturn(handyman);
        Mockito.when(context.getZoo()).thenReturn(Mockito.mock(Zoo.class));
        return context;
    }

    private NameValidator givenNameTest(boolean value) {
        NameValidator mock = Mockito.mock(NameValidator.class);
        Mockito.when(mock.test(any(NameDto.class))).thenReturn(value);
        return mock;
    }

    private FindHandyman givenFindHandyman() {
        FindHandyman mock = Mockito.mock(FindHandyman.class);
        Mockito.when(mock.find(Mockito.any(Zoo.class), Mockito.anyString())).thenReturn(Mockito.mock(Handyman.class));
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheNewNameIsCorrect() {
        // Given
        HandymanRenameContext context = givenContext(Mockito.mock(Handyman.class));
        NameValidator nameValidator = givenNameTest(true);
        FindHandyman findHandyman = givenFindHandyman();
        HandymanRenameValidator validator = new HandymanRenameValidator(nameValidator,
                findHandyman);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheNewNameIsKO() {
        // Given
        HandymanRenameContext context = givenContext(Mockito.mock(Handyman.class));
        NameValidator nameValidator = givenNameTest(false);
        FindHandyman findHandyman = givenFindHandyman();
        HandymanRenameValidator validator = new HandymanRenameValidator(
                nameValidator,
                findHandyman);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenTheCurrentNameIsNotTheOneOfAKeeper() {
        // Given
        HandymanRenameContext context = givenContext(null);
              NameValidator nameValidator = givenNameTest(true);
        FindHandyman findHandyman = givenFindHandyman();
        HandymanRenameValidator validator = new HandymanRenameValidator(
                nameValidator,
                findHandyman);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
