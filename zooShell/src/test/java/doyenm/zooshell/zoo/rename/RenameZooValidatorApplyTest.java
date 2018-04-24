package doyenm.zooshell.zoo.rename;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.zoo.creation.ZooCreationContext;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class RenameZooValidatorApplyTest {

    private RenameZooValidator subject;
    private NameValidator nameValidator;
    private ZooContext context;

    @Before
    public void setUp() {
        nameValidator = mock(NameValidator.class);
        subject = new RenameZooValidator(nameValidator);

        context = mock(ZooContext.class);
        when(context.getSaveName()).thenReturn(RandomStringUtils.randomAlphabetic(10));
        when(context.getErrors()).thenCallRealMethod();
        doCallRealMethod().when(context).setErrors(any());
        context.setErrors(Lists.newArrayList());
    }

    @Test
    public void shouldReturnNoErrorWhenEverythingIsOk() {
        // Given
        givenNameValidator(true);
        // When
        ZooContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).isEmpty();

    }

    @Test
    public void shouldReturnOneErrorWhenTheNameIsIncorrect() {
        // Given
        givenNameValidator(false);
        // When
        ZooContext result = subject.apply(context);
        // Then
        assertThat(result).isNotNull();
        assertThat(result.getErrors()).isNotNull();
        assertThat(result.getErrors()).hasSize(1);
    }

    private void givenNameValidator(boolean bool) {
        when(nameValidator.test(any(NameDto.class)))
                .thenReturn(bool);
    }
}
