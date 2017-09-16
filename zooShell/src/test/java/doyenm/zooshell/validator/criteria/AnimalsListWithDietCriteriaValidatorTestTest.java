package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author doyenm
 */
public class AnimalsListWithDietCriteriaValidatorTestTest {

    private LsWithCriteriaContext givenContext(Map<String, Diet> diets, List<String> dietsStr) {
        LsWithCriteriaContext context = Mockito.mock(LsWithCriteriaContext.class);
        Zoo zoo = Mockito.mock(Zoo.class);
        Mockito.when(context.getZoo()).thenReturn(zoo);
        Mockito.when(context.getConvertedDiets()).thenReturn(diets);
        Mockito.when(context.getDiets()).thenReturn(dietsStr);
        return context;
    }

    private FindingDietFunction givenFindingDiet() {
        FindingDietFunction mock = Mockito.mock(FindingDietFunction.class);
        FindingDietContext context = Mockito.mock(FindingDietContext.class);
        Mockito.when(context.getConvertedDiet()).thenReturn(null);
        Mockito.when(mock.apply(Mockito.anyObject())).thenReturn(context);
        return mock;
    }

    @Test
    public void shouldReturnTrueWhenTheAllTheDietHaveBeenConverted() {
        // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                new ArrayList<String>());
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    public void shouldReturnFalseWhenTheConvertedDietsAreNull() {
         // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                null,
                new ArrayList<String>());
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    public void shouldReturnFalseWhenSomeOfTheRequestedDietsDoNotExist() {
      // Given
        FindingDietFunction findingDietFunction = givenFindingDiet();
        LsWithCriteriaContext context = givenContext(
                new HashMap<String, Diet>(),
                Arrays.asList(RandomStringUtils.randomAlphabetic(10)));
        AnimalsListWithDietCriteriaValidator validator = new AnimalsListWithDietCriteriaValidator(findingDietFunction);
        // When
        boolean result = validator.test(context);
        // Then
        Assertions.assertThat(result)
                .isFalse();
    }
}
