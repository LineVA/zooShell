package doyenm.zooshell.validator;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.types.UpdatePaddockTypeContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.context.FindingPaddockTypeContext;
import doyenm.zooshell.common.function.FindingPaddockTypeFunction;
import doyenm.zooshell.paddock.types.UpdatePaddockTypeValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeValidatorTestTest {

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        return pad;
    }

    private UpdatePaddockTypeContext givenContextWithZooTypeAndPad(
            Zoo zoo, PaddockType type, Paddock pad) {
        UpdatePaddockTypeContext context = Mockito.mock(UpdatePaddockTypeContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Mockito.when(context.getConvertedType()).thenReturn(type);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        return context;
    }

    private Zoo givenZoo() {
        Zoo zoo = Mockito.mock(Zoo.class);
        return zoo;
    }

    private FindPaddock givenFindWithPad(Paddock pad) {
        FindPaddock find = Mockito.mock(FindPaddock.class);
        Mockito.doReturn(pad).when(find).find(Mockito.any(Zoo.class), Mockito.anyString());
        return find;
    }

    private FindingPaddockTypeFunction givenFindingPaddockType(PaddockType type) {
        FindingPaddockTypeFunction finding = Mockito.mock(FindingPaddockTypeFunction.class);
        Mockito.doAnswer(new Answer<FindingPaddockTypeContext>() {
            @Override
            public FindingPaddockTypeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingPaddockTypeContext) args[0];
            }
        })
                .when(finding).apply(Mockito.any(FindingPaddockTypeContext.class));
        return finding;
    }

    @Test
    public void shouldReturnTrueIfThePaddockAndTheBiomeExist() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        FindingPaddockTypeFunction findType = givenFindingPaddockType(PaddockType.AQUARIUM);
        UpdatePaddockTypeContext context = givenContextWithZooTypeAndPad(zoo, PaddockType.AQUARIUM, pad);
        UpdatePaddockTypeValidator validator = new UpdatePaddockTypeValidator(find, findType);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotThePaddockType() {
         // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        FindingPaddockTypeFunction findType = givenFindingPaddockType(PaddockType.AQUARIUM);
        UpdatePaddockTypeContext context = givenContextWithZooTypeAndPad(zoo, null, pad);
        UpdatePaddockTypeValidator validator = new UpdatePaddockTypeValidator(find, findType);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void shouldReturnTrueIfThePaddockTypeExistsButNotThePaddock() {
          // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(null);
        FindingPaddockTypeFunction findType = givenFindingPaddockType(PaddockType.AQUARIUM);
        UpdatePaddockTypeContext context = givenContextWithZooTypeAndPad(zoo, PaddockType.AQUARIUM, pad);
        UpdatePaddockTypeValidator validator = new UpdatePaddockTypeValidator(find, findType);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
