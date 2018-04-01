package doyenm.zooshell.validator;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.biomes.UpdateBiomeContext;
import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.context.FindingBiomeContext;
import doyenm.zooshell.common.function.FindingBiomeFunction;
import doyenm.zooshell.paddock.biomes.UpdateBiomeValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author doyenm
 */
public class UpdateBiomeValidatorTestTest {

    private Paddock givenPaddock() {
        Paddock pad = Mockito.mock(Paddock.class);
        return pad;
    }

    private UpdateBiomeContext givenContextWithZooBiomeAndPad(
            Zoo zoo, Biome biome, Paddock pad) {
        UpdateBiomeContext context = Mockito.mock(UpdateBiomeContext.class);
        Mockito.when(context.getConvertedPaddock()).thenReturn(pad);
        Mockito.when(context.getConvertedBiome()).thenReturn(biome);
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

    private FindingBiomeFunction givenFindingBiome(Biome biome) {
        FindingBiomeFunction finding = Mockito.mock(FindingBiomeFunction.class);
        Mockito.doAnswer(new Answer<FindingBiomeContext>() {
            @Override
            public FindingBiomeContext answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return (FindingBiomeContext) args[0];
            }
        })
                .when(finding).apply(Mockito.any(FindingBiomeContext.class));
        return finding;
    }

    @Test
    public void shouldReturnTrueIfThePaddockAndTheBiomeExist() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        FindingBiomeFunction findBiome = givenFindingBiome(Biome.DESERT);
        UpdateBiomeContext context = givenContextWithZooBiomeAndPad(zoo, Biome.DESERT, pad);
        UpdateBiomeValidator validator = new UpdateBiomeValidator(find, findBiome);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isTrue();
    }
    
    @Test
    public void shouldReturnFalseIfThePaddockExistsButNotTheBiome() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(pad);
        FindingBiomeFunction findBiome = givenFindingBiome(Biome.DESERT);
        UpdateBiomeContext context = givenContextWithZooBiomeAndPad(zoo, null, pad);
        UpdateBiomeValidator validator = new UpdateBiomeValidator(find, findBiome);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
    
    @Test
    public void shouldReturnTrueIfTheBiomeExistsButNotThePaddock() {
        // Given
        Paddock pad = givenPaddock();
        Zoo zoo = givenZoo();
        FindPaddock find = givenFindWithPad(null);
        FindingBiomeFunction findBiome = givenFindingBiome(Biome.DESERT);
        UpdateBiomeContext context = givenContextWithZooBiomeAndPad(zoo, Biome.DESERT, pad);
        UpdateBiomeValidator validator = new UpdateBiomeValidator(find, findBiome);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result).isFalse();
    }
}
