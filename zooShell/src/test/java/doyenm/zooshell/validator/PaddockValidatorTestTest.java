package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class PaddockValidatorTestTest {

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        return pad;
    }

    private PaddockContext givenContextWithZooAndPad(
            Zoo zoo, Paddock pad) {
        PaddockContext context = Mockito.mock(PaddockContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
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

    @Test
    public void shouldReturnTrueIfThePaddockExist() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        PaddockContext context = givenContextWithZooAndPad(zoo, pad);
        PaddockValidator validator = new PaddockValidator(find);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseIfThePaddockDoesNotExist(){
           // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(null);
        PaddockContext context = givenContextWithZooAndPad(zoo, pad);
        PaddockValidator validator = new PaddockValidator(find);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
